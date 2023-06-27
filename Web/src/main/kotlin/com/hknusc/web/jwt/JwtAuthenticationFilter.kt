package com.hknusc.web.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtAuthenticationFilter(val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest: HttpServletRequest = request as HttpServletRequest
        val httpResponse: HttpServletResponse = response as HttpServletResponse

        val bearerToken = httpRequest.getHeader(JwtTokenProvider.Access_Key)
        val accessToken: String? = jwtTokenProvider.resolveToken(bearerToken)
        if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
            val authentication: Authentication = jwtTokenProvider.getAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(httpRequest, httpResponse)
    }
}