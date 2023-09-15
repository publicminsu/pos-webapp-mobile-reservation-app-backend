package com.hknusc.web.config

import com.hknusc.web.util.exception.ErrorUtility
import com.hknusc.web.util.jwt.JwtAccessDeniedHandler
import com.hknusc.web.util.jwt.JwtAuthenticationEntryPoint
import com.hknusc.web.util.jwt.JwtSecurityConfig
import com.hknusc.web.util.jwt.JwtTokenProvider
import com.hknusc.web.util.type.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val errorUtility: ErrorUtility
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic().disable()
            .cors().disable()
            .csrf().disable()

            .authorizeHttpRequests()
            .requestMatchers("/auth/**", "/users/**", "/reviews/**", "/check/**").permitAll()
            .anyRequest().hasRole(Role.USER.toString())
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)

            .accessDeniedHandler(jwtAccessDeniedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .formLogin().disable()

            .apply(JwtSecurityConfig(jwtTokenProvider, errorUtility))
        return http.build()
    }
}
