package com.hknusc.web.util.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.hknusc.web.dto.ErrorResponseDTO
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component

@Component
class ErrorUtility {
    public fun errorCodeToErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status.value()
        response.contentType = "application/json"
        response.characterEncoding = "utf8"

        val errorResponse = ObjectMapper().writeValueAsString(ErrorResponseDTO(errorCode.status, errorCode.message))

        response.writer.write(errorResponse)
    }
}