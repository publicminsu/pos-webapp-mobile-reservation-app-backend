package com.hknusc.web.util.jwt

import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.exception.ErrorUtility
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtAccessDeniedHandler(private val errorUtility: ErrorUtility) : AccessDeniedHandler {
    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        errorUtility.generateErrorResponse(ErrorCode.FORBIDDEN_TOKEN, response)
    }
}