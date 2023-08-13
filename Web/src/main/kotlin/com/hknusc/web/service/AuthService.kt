package com.hknusc.web.service

import com.hknusc.web.dto.auth.ConfirmResetPasswordDTO
import com.hknusc.web.dto.auth.LoginDTO
import com.hknusc.web.dto.auth.PasswordDBEditDTO
import com.hknusc.web.dto.auth.ResetPasswordDTO
import com.hknusc.web.dto.user.UserDTO
import com.hknusc.web.repository.AuthRepository
import com.hknusc.web.repository.UserRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtAuthInfo
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    @param:Value("\${frontEnd.urlPath}") private val frontEndUrl: String,
    private val tokenProvider: JwtTokenProvider,
    private val mailSender: JavaMailSender,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) {

    fun authorize(loginDTO: LoginDTO): ResponseEntity<Any> {
        lateinit var user: UserDTO

        try {
            user = userRepository.getUserByUserEmail(loginDTO.email)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.LOGIN_FAIL)
        }

        if (!passwordEncoder.matches(loginDTO.password, user.password)) {
            throw CustomException(ErrorCode.LOGIN_FAIL)
        }

        val httpHeaders: HttpHeaders = tokenProvider.generateTokenHeader(user.id, user.email)
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }

    fun logout(bearerRefreshToken: String): ResponseEntity<Any> {
        val refreshToken = tokenProvider.resolveToken(bearerRefreshToken)

        tokenProvider.validateToken(refreshToken)

        val userId = tokenProvider.findUserIdByJWT(refreshToken)
        val savedRefreshTokenDTO = authRepository.getRefreshToken(userId)
        val savedRefreshToken = savedRefreshTokenDTO.refreshToken

        //갱신해둔 값 그대로가 아니면 잘못된 인증
        if (refreshToken != savedRefreshToken) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }
        authRepository.removeRefreshToken(savedRefreshTokenDTO.id)
        return ResponseEntity(HttpStatus.OK)
    }

    fun refresh(oldBearerAccessToken: String, oldBearerRefreshToken: String): ResponseEntity<Any> {

        val oldAccessToken = tokenProvider.resolveToken(oldBearerAccessToken)
        val oldRefreshToken = tokenProvider.resolveToken(oldBearerRefreshToken)

        //RefreshToken 검증.
        tokenProvider.validateToken(oldRefreshToken.toString())

        //AccessToken 정보 가져오기
        val claims = tokenProvider.findClaimsByJWT(oldAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims).toInt()
        val userEmail = tokenProvider.findUserEmailByClaims(claims)
        val userStoreId: Int = try {
            tokenProvider.findUserStoreIdByClaims(claims).toInt()
        } catch (e: Exception) {
            0
        }

        //가져온 정보를 토대로 DB에 저장된 RefreshToken 가져오기
        val savedRefreshToken = authRepository.getRefreshToken(userId).refreshToken

        //저장된 RefreshToken 올바른지 확인
        tokenProvider.validateToken(savedRefreshToken)

        //동일하지 않은 토큰인지 확인
        if (oldRefreshToken != savedRefreshToken) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }

        val httpHeaders: HttpHeaders = tokenProvider.generateTokenHeader(userId, userEmail, userStoreId)
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }

    fun sendResetPasswordEmail(resetPasswordDTO: ResetPasswordDTO) {
        lateinit var user: UserDTO
        val email: String = resetPasswordDTO.email

        try {
            user = userRepository.getUserByUserEmail(email)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        val jwtAuthInfo = JwtAuthInfo(user.id, user.email, 0)
        val token: String = tokenProvider.generateAccessToken(jwtAuthInfo)

        val mailMessage = SimpleMailMessage()
        mailMessage.subject = "비밀번호 재설정"
        mailMessage.text = "링크: $frontEndUrl/$token"
        mailMessage.setTo(email)

        mailSender.send(mailMessage)
    }

    fun confirmResetPasswordEmail(confirmResetPasswordDTO: ConfirmResetPasswordDTO) {
        val token: String = confirmResetPasswordDTO.token

        tokenProvider.validateToken(token)

        if (confirmResetPasswordDTO.password != confirmResetPasswordDTO.confirmPassword) {
            throw CustomException(ErrorCode.PASSWORD_NOT_SAME)
        }

        val userId: Int = tokenProvider.findUserIdByJWT(token)
        val encodedPassword = passwordEncoder.encode(confirmResetPasswordDTO.password)

        val passwordDBEditDTO = PasswordDBEditDTO(userId, encodedPassword)
        userRepository.editPassword(passwordDBEditDTO)
    }
}