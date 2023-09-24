package com.hknusc.web.util.Interceptor

import com.hknusc.web.util.jwt.JWTTokenProvider
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
            val userStoreId = try {
                tokenProvider.findUserStoreIdByClaims(claims)
            } catch (_: Exception) {
                0
            }

            request.setAttribute("userId", userId)
            request.setAttribute("userEmail", userEmail)
            request.setAttribute("userStoreId", userStoreId)
        }
        return true
    }
}
