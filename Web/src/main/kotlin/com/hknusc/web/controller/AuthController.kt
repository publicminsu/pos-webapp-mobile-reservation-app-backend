package com.hknusc.web.controller

import com.hknusc.web.dto.auth.ConfirmEmailDTO
import com.hknusc.web.dto.auth.ConfirmResetPasswordDTO
import com.hknusc.web.dto.auth.LoginDTO
import com.hknusc.web.dto.auth.ResetPasswordDTO
import com.hknusc.web.util.jwt.JwtTokenProvider
import com.hknusc.web.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthController(private val authService: AuthService) {
    //인증
    @PostMapping
    fun authorize(loginDTO: LoginDTO) = authService.authorize(loginDTO)

    //인증 삭제
    @DeleteMapping
    fun logout(@RequestHeader(JwtTokenProvider.Refresh_Key) refreshToken: String) = authService.logout(refreshToken)

    //인증 재발급 (만료된 인증을 새로운 인증으로 교체)
    @GetMapping("refresh")
    fun refresh(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @RequestHeader(JwtTokenProvider.Refresh_Key) refreshToken: String
    ) = authService.refresh(accessToken, refreshToken)

    //이메일 인증 확인
    @PostMapping("email")
    fun confirmEmail(confirmEmailDTO: ConfirmEmailDTO) = authService.confirmEmail(confirmEmailDTO)

    //비밀번호 재설정 이메일 보내기
    @PostMapping("reset")
    fun sendResetPasswordEmail(resetPasswordDTO: ResetPasswordDTO) =
        authService.sendResetPasswordEmail(resetPasswordDTO)

    //비밀번호 재설정 이메일 확인
    @PostMapping("reset/confirm")
    fun confirmResetPasswordEmail(confirmResetPasswordDTO: ConfirmResetPasswordDTO) =
        authService.confirmResetPasswordEmail(confirmResetPasswordDTO)
}
