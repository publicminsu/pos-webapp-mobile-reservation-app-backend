package com.hknusc.web.util.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JWTInterceptor(private val tokenProvider: JWTTokenProvider) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val bearerAccessToken = request.getHeader(JWTTokenProvider.ACCESS_KEY) ?: return true

            val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
            val userId = tokenProvider.findUserIdByClaims(claims)
            val userEmail = tokenProvider.findUserEmailByClaims(claims)

            request.setAttribute("userId", userId)
            request.setAttribute("userEmail", userEmail)
        }
        return true
    }
}
