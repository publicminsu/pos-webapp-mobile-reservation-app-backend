package com.hknusc.web.util.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.hknusc.web.dto.ErrorResponseDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatusCode
import org.springframework.web.filter.OncePerRequestFilter
import java.security.SignatureException

class JWTExceptionHandler : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CustomException) {
            errorCodeToErrorResponse(e.errorCode, response)
        } catch (e: Exception) {
            errorCodeToErrorResponse(ErrorCode.BAD_TOKEN, response)
        }
    }

    private fun errorCodeToErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status.value()
        response.contentType = "application/json"
        response.characterEncoding = "utf8"

        val errorResponse = ObjectMapper().writeValueAsString(ErrorResponseDTO(errorCode.status, errorCode.message))

        response.writer.write(errorResponse)
    }
}