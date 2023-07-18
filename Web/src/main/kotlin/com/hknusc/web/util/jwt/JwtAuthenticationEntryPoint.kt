package com.hknusc.web.util.jwt

import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.exception.ErrorUtility
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtAuthenticationEntryPoint(private val errorUtility: ErrorUtility) : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        errorUtility.errorCodeToErrorResponse(ErrorCode.INVALID_TOKEN, response)
    }
}