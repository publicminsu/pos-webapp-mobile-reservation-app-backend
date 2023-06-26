package com.hknusc.web.controller

import com.hknusc.web.dto.LoginDTO
import com.hknusc.web.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController() {
    @Autowired
    lateinit var authService: AuthService
    @PostMapping
    fun authorize(loginDTO: LoginDTO)=authService.authorize(loginDTO);
}