package com.hknusc.web.controller

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.util.jwt.JwtTokenProvider
import com.hknusc.web.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthController(private val authService: AuthService) {
    @PostMapping
    fun authorize(loginDTO: LoginDTO) = authService.authorize(loginDTO)

    @DeleteMapping
    fun logout(@RequestHeader(JwtTokenProvider.Refresh_Key) refreshToken: String) = authService.logout(refreshToken)

    @GetMapping("refresh")
    fun refresh(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @RequestHeader(JwtTokenProvider.Refresh_Key) refreshToken: String
    ) = authService.refresh(accessToken, refreshToken)
}