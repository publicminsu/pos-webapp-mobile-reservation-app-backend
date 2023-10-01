package com.hknusc.web.config

import com.hknusc.web.util.exception.ErrorUtility
import com.hknusc.web.util.jwt.JWTAccessDeniedHandler
import com.hknusc.web.util.jwt.JWTAuthenticationEntryPoint
import com.hknusc.web.util.jwt.JWTSecurityConfig
import com.hknusc.web.util.jwt.JWTTokenProvider
import com.hknusc.web.util.type.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val corsConfig: CorsConfig,
    private val jwtTokenProvider: JWTTokenProvider,
    private val jwtAuthenticationEntryPoint: JWTAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JWTAccessDeniedHandler,
    private val errorUtility: ErrorUtility
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic().disable()
            .cors().configurationSource(corsConfig.corsConfigurationSource()).and()
            .csrf().disable()

            .authorizeHttpRequests()
            .requestMatchers("/auth/**", "/users/**", "/reviews/**", "/check/**").permitAll()
            .requestMatchers("/notifications/**").permitAll()
            .anyRequest().hasRole(Role.USER.toString())
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)

            .accessDeniedHandler(jwtAccessDeniedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .formLogin().disable()

            .apply(JWTSecurityConfig(jwtTokenProvider, errorUtility))
        return http.build()
    }
}
