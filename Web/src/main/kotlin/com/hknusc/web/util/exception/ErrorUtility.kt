package com.hknusc.web.util.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.hknusc.web.dto.ErrorResponseDTO
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ErrorUtility {
    fun generateErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        generateErrorResponse(errorCode.status, errorCode.message, response)
    }

    fun generateErrorResponse(status: HttpStatus, message: String?, response: HttpServletResponse) {
        response.status = status.value()
        response.contentType = "application/json"
        response.characterEncoding = "utf8"

        val errorResponse = ObjectMapper().writeValueAsString(ErrorResponseDTO(status, message))

        response.writer.write(errorResponse)
    }
}