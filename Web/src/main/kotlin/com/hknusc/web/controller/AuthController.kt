package com.hknusc.web.controller

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.dto.TokenDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    private val tokenProvider:JwtTokenProvider,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder) {
    @PostMapping
    fun authorize(loginDTO: LoginDTO):ResponseEntity<TokenDTO>{
        var user=userService.getUserByUserEmail(loginDTO.email)
        if(!passwordEncoder.matches(loginDTO.password,user.password)){
            return ResponseEntity(TokenDTO("WrongPassword"),HttpStatus.UNAUTHORIZED)
        }
        val accessToken=tokenProvider.generateAccessToken(user);
        val refreshToken=tokenProvider.generateRefreshToken(user);
        var httpHeaders:HttpHeaders= HttpHeaders()
        httpHeaders.add(JwtTokenProvider.Access_Key, "Bearer $accessToken")
        httpHeaders.add(JwtTokenProvider.Refresh_Key, "Bearer $refreshToken")
        return ResponseEntity(httpHeaders,HttpStatus.OK)
    }
}