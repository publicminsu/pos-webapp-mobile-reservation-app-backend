package com.hknusc.web.util.jwt

import com.hknusc.web.dto.auth.RefreshTokenSaveDTO
import com.hknusc.web.repository.AuthRepository
import com.hknusc.web.service.UserDetailService
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JWTTokenProvider(
    @param:Value("\${jwt.secretKey}") private val secretKey: String,
    private val userDetailService: UserDetailService,
    private val authRepository: AuthRepository
) : InitializingBean {
    val logger = LoggerFactory.getLogger(JWTTokenProvider::class.java)
    lateinit var key: Key
    val accessTokenExpireTime = 30 * 60 * 1000L//30분
    val refreshTokenExpireTime = 60 * 60 * 24 * 1000L
    override fun afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun generateRefreshToken(jwtAuthenticationInfo: JWTAuthenticationInfo): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "REFRESH_TOKEN")
            .setHeaderParam("alg", "HS256")
            .setSubject(jwtAuthenticationInfo.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + refreshTokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateAccessToken(jwtAuthenticationInfo: JWTAuthenticationInfo): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "ACCESS_TOKEN")
            .setHeaderParam("alg", "HS256")
            .setSubject(jwtAuthenticationInfo.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenExpireTime))
            .claim("userId", jwtAuthenticationInfo.id)
            .claim("userEmail", jwtAuthenticationInfo.email)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun findClaimsByJWT(token: String?): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    fun findUserIdByJWT(token: String?): Int {
        return findClaimsByJWT(token).subject.toInt()
    }

    fun findUserIdByClaims(claims: Claims): Int {
        return claims["userId"].toString().toInt()
    }

    fun findUserEmailByClaims(claims: Claims): String {
        return claims["userEmail"].toString()
    }

    fun findClaimsByBearerAccessToken(bearerAccessToken: String): Claims {
        val accessToken = resolveToken(bearerAccessToken)
        return findClaimsByJWT(accessToken)
    }

    fun findUserIdByBearerAccessToken(bearerAccessToken: String): Int {
        val accessToken = resolveToken(bearerAccessToken)
        return findUserIdByJWT(accessToken)
    }

    fun validateToken(token: String?) {
        if (token.isNullOrEmpty())
            throw CustomException(ErrorCode.EMPTY_TOKEN)
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw CustomException(ErrorCode.EXPIRED_TOKEN)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailService.loadUserByUsername(findUserIdByJWT(token).toString())
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun resolveToken(bearerToken: String?): String? {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }

    /*
    RTR (Refresh Token Rotation) RefreshToken 사용될 때마다 재발급
     */
    fun generateTokenHeader(userId: Int, userEmail: String): HttpHeaders {
        val JWTAuthenticationInfo = JWTAuthenticationInfo(userId, userEmail)
        val accessToken = generateAccessToken(JWTAuthenticationInfo)
        val refreshToken = generateRefreshToken(JWTAuthenticationInfo)

        val refreshTokenSaveDTO = RefreshTokenSaveDTO(userId, refreshToken)
        authRepository.saveRefreshToken(refreshTokenSaveDTO)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(ACCESS_KEY, "Bearer $accessToken")
        httpHeaders.add(REFRESH_KEY, "Bearer $refreshToken")
        return httpHeaders
    }

    companion object {
        const val ACCESS_KEY = "access_token"
        const val REFRESH_KEY = "refresh_token"
    }
}
