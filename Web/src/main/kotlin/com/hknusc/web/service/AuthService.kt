package com.hknusc.web.service

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.dto.RefreshTokenDTO
import com.hknusc.web.dto.TokenDTO
import com.hknusc.web.jwt.JwtAuthInfo
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.repository.AuthRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenProvider: JwtTokenProvider,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val authRepository: AuthRepository
) {

    fun authorize(loginDTO: LoginDTO): ResponseEntity<TokenDTO> {
        val user = userService.getUserByUserEmail(loginDTO.email)

        if (!passwordEncoder.matches(loginDTO.password, user.password)) {
            return ResponseEntity(TokenDTO("WrongPassword"), HttpStatus.UNAUTHORIZED)
        }

        val httpHeaders: HttpHeaders = generateTokenHeader(user.id, user.email)
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }

    fun refresh(oldBearerAccessToken: String, oldBearerRefreshToken: String): ResponseEntity<TokenDTO> {

        val oldAccessToken = tokenProvider.resolveToken(oldBearerAccessToken);
        val oldRefreshToken = tokenProvider.resolveToken(oldBearerRefreshToken);

        //RefreshToken 검증.
        if (!tokenProvider.validateToken(oldRefreshToken.toString())) {
            return ResponseEntity(TokenDTO("Authentication Is Not Valid"), HttpStatus.UNAUTHORIZED)
        }

        //AccessToken 정보 가져오기
        val claims = tokenProvider.findClaimsByJWT(oldAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims).toInt()
        val userEmail = tokenProvider.findUserEmailByClaims(claims)

        //가져온 정보를 토대로 DB에 저장된 RefreshToken 가져오기
        val savedRefreshToken = authRepository.getRefreshToken(userId).refreshToken.toString()

        //저장된 RefreshToken 기한 지났는지 확인, 동일하지 않은 토큰인지 확인
        if (!tokenProvider.validateToken(savedRefreshToken) || oldRefreshToken != savedRefreshToken) {
            return ResponseEntity(TokenDTO("Authentication Is Not Valid"), HttpStatus.UNAUTHORIZED)
        }

        val httpHeaders: HttpHeaders = generateTokenHeader(userId, userEmail)
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }

    /*
    RTR (Refresh Token Rotation) RefreshToken 사용될 때마다 재발급
     */
    fun generateTokenHeader(userId: Int, userEmail: String): HttpHeaders {
        val jwtAuthInfo = JwtAuthInfo(userId, userEmail)
        val accessToken = tokenProvider.generateAccessToken(jwtAuthInfo)
        val refreshToken = tokenProvider.generateRefreshToken(jwtAuthInfo)

        val refreshTokenDTO = RefreshTokenDTO(accountId = userId, refreshToken = refreshToken)
        authRepository.saveRefreshToken(refreshTokenDTO)

        val httpHeaders: HttpHeaders = HttpHeaders()
        httpHeaders.add(JwtTokenProvider.Access_Key, "Bearer $accessToken")
        httpHeaders.add(JwtTokenProvider.Refresh_Key, "Bearer $refreshToken")
        return httpHeaders
    }
}