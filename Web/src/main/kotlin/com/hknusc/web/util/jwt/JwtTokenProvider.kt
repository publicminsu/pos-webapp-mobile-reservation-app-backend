package com.hknusc.web.util.jwt

import com.hknusc.web.dto.auth.RefreshTokenDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.repository.AuthRepository
import com.hknusc.web.service.UserDetailService
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
class JwtTokenProvider(
    @param:Value("\${jwt.secretKey}") private val secretKey: String,
    private val userDetailService: UserDetailService,
    private val authRepository: AuthRepository
) : InitializingBean {
    val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    lateinit var key: Key
    val accessTokenExpireTime = 30 * 60 * 1000L//30분
    val refreshTokenExpireTime = 60 * 60 * 24 * 1000L
    override fun afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun generateRefreshToken(jwtAuthInfo: JwtAuthInfo): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "REFRESH_TOKEN")
            .setHeaderParam("alg", "HS256")
            .setSubject(jwtAuthInfo.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + refreshTokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateAccessToken(jwtAuthInfo: JwtAuthInfo): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "ACCESS_TOKEN")
            .setHeaderParam("alg", "HS256")
            .setSubject(jwtAuthInfo.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenExpireTime))
            .claim("userId", jwtAuthInfo.id)
            .claim("userEmail", jwtAuthInfo.email)
            .claim("userStoreId", jwtAuthInfo.storeId)
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

    fun findUserIdByClaims(claims: Claims): String {
        return claims["userId"].toString()
    }

    fun findUserEmailByClaims(claims: Claims): String {
        return claims["userEmail"].toString()
    }

    fun findUserStoreIdByClaims(claims: Claims): String {
        val storeId = claims["userStoreId"].toString()
        if (storeId == "0")
            throw CustomException(ErrorCode.STORE_NOT_OPEN)
        return storeId
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
    fun generateTokenHeader(userId: Int, userEmail: String, userStoreId: Int = 0): HttpHeaders {
        val jwtAuthInfo = JwtAuthInfo(userId, userEmail, userStoreId)
        val accessToken = generateAccessToken(jwtAuthInfo)
        val refreshToken = generateRefreshToken(jwtAuthInfo)

        val refreshTokenDTO = RefreshTokenDTO(accountId = userId, refreshToken = refreshToken)
        authRepository.saveRefreshToken(refreshTokenDTO)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(Access_Key, "Bearer $accessToken")
        httpHeaders.add(Refresh_Key, "Bearer $refreshToken")
        return httpHeaders
    }

    fun getUserStoreIdByBearerAccessToken(bearerAccessToken: String): Int {
        val accessToken = resolveToken(bearerAccessToken)
        val claims = findClaimsByJWT(accessToken)
        return findUserStoreIdByClaims(claims).toInt()
    }

    companion object {
        const val Access_Key = "access_token"
        const val Refresh_Key = "refresh_token"
    }
}