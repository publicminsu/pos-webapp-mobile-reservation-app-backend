package com.hknusc.web.controller

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController() {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping
    fun authorize(loginDTO: LoginDTO) = authService.authorize(loginDTO);

    @GetMapping("/refresh")
    fun refresh(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @RequestHeader(JwtTokenProvider.Refresh_Key) refreshToken: String
    ) = authService.refresh(accessToken, refreshToken)
}