package com.hknusc.web.util.jwt

import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.exception.ErrorUtility
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter

class JWTExceptionHandler(private val errorUtility: ErrorUtility) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CustomException) {
            errorUtility.generateErrorResponse(e.errorCode, response)
        } catch (e: Exception) {
            errorUtility.generateErrorResponse(HttpStatus.BAD_REQUEST, e.message, response)
        }
    }
}