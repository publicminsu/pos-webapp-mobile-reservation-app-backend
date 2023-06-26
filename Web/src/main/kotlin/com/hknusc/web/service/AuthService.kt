package com.hknusc.web.service

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.dto.TokenDTO
import com.hknusc.web.jwt.JwtAuthInfo
import com.hknusc.web.jwt.JwtTokenProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenProvider: JwtTokenProvider,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {
    fun authorize(loginDTO: LoginDTO): ResponseEntity<TokenDTO> {
        val user = userService.getUserByUserEmail(loginDTO.email)

        if (!passwordEncoder.matches(loginDTO.password, user.password)) {
            return ResponseEntity(TokenDTO("WrongPassword"), HttpStatus.UNAUTHORIZED)
        }
        val jwtAuthInfo=JwtAuthInfo(user.id,user.email)
        val accessToken = tokenProvider.generateAccessToken(jwtAuthInfo)
        val refreshToken = tokenProvider.generateRefreshToken(jwtAuthInfo)

        val httpHeaders: HttpHeaders = HttpHeaders()
        httpHeaders.add(JwtTokenProvider.Access_Key, "Bearer $accessToken")
        httpHeaders.add(JwtTokenProvider.Refresh_Key, "Bearer $refreshToken")
        return ResponseEntity(httpHeaders, HttpStatus.OK)
    }
}