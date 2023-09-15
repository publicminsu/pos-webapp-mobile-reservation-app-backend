package com.hknusc.web.util.jwt

import com.hknusc.web.util.exception.ErrorUtility
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(private val tokenProvider: JwtTokenProvider, private val errorUtility: ErrorUtility) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(
            JwtAuthenticationFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        ).addFilterBefore(JWTExceptionHandler(errorUtility), JwtAuthenticationFilter::class.java)
    }
}