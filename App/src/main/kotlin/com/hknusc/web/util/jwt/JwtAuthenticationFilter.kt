package com.hknusc.web.util.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearerToken = request.getHeader(JwtTokenProvider.ACCESS_KEY)

        if (bearerToken != null) {
            val accessToken: String? = jwtTokenProvider.resolveToken(bearerToken)

            jwtTokenProvider.validateToken(accessToken)
            val authentication: Authentication = jwtTokenProvider.getAuthentication(accessToken.toString())
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        val method = request.method

        return if (path.startsWith("/auth")) {
            true
        } else if (path.equals("/users") && method == "POST") {
            true
        } else
            false
    }
}
