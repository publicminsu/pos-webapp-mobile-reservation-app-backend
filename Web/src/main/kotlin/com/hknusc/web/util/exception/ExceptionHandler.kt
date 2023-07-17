package com.hknusc.web.util.exception

import com.hknusc.web.dto.ErrorResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorResponseDTO> {
        val errorCode: ErrorCode = ex.errorCode
        return ResponseEntity.status(errorCode.status).body(ErrorResponseDTO(errorCode.status, errorCode.message))
    }
}