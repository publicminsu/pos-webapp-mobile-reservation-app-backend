package com.hknusc.web.service

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.dto.RefreshTokenDTO
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.exception.CustomException
import com.hknusc.web.exception.ErrorCode
import com.hknusc.web.jwt.JwtAuthInfo
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.repository.AuthRepository
import com.hknusc.web.repository.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authRepository: AuthRepository
) {

    fun authorize(loginDTO: LoginDTO): ResponseEntity<Any> {
        lateinit var user: UserDTO
        try{
            user=userRepository.getUserByUserEmail(loginDTO.email)!!
        }catch (e:Exception){
            throw CustomException(ErrorCode.LOGIN_FAIL)
        }

        if (!passwordEncoder.matches(loginDTO.password, user.password)) {
            throw CustomException(ErrorCode.LOGIN_FAIL)
        }

        val httpHeaders: HttpHeaders = generateTokenHeader(user.id, user.email)
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }

    fun logout(bearerRefreshToken: String): ResponseEntity<Any> {
        val refreshToken = tokenProvider.resolveToken(bearerRefreshToken);

        tokenProvider.validateToken(refreshToken)

        val userId = tokenProvider.findUserIdByJWT(refreshToken)
        val savedRefreshTokenDTO = authRepository.getRefreshToken(userId)
        val savedRefreshToken = savedRefreshTokenDTO.refreshToken

        //갱신해둔 값 그대로가 아니면 잘못된 인증
        if (refreshToken != savedRefreshToken) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }
        savedRefreshTokenDTO.refreshToken = null
        authRepository.removeRefreshToken(savedRefreshTokenDTO)
        return ResponseEntity(HttpStatus.OK)
    }

    fun refresh(oldBearerAccessToken: String, oldBearerRefreshToken: String): ResponseEntity<Any> {

        val oldAccessToken = tokenProvider.resolveToken(oldBearerAccessToken);
        val oldRefreshToken = tokenProvider.resolveToken(oldBearerRefreshToken);

        //RefreshToken 검증.
        tokenProvider.validateToken(oldRefreshToken.toString())

        //AccessToken 정보 가져오기
        val claims = tokenProvider.findClaimsByJWT(oldAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims).toInt()
        val userEmail = tokenProvider.findUserEmailByClaims(claims)

        //가져온 정보를 토대로 DB에 저장된 RefreshToken 가져오기
        val savedRefreshToken = authRepository.getRefreshToken(userId).refreshToken.toString()

        //저장된 RefreshToken 올바른지 확인
        tokenProvider.validateToken(savedRefreshToken)

        //동일하지 않은 토큰인지 확인
        if (oldRefreshToken != savedRefreshToken) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
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