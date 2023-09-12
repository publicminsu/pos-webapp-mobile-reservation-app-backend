package com.hknusc.web.util.exception

import com.hknusc.web.dto.ErrorResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException


@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorResponseDTO> {
        val errorCode: ErrorCode = ex.errorCode
        return ResponseEntity.status(errorCode.status).body(ErrorResponseDTO(errorCode.status, errorCode.message))
    }

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(ex: MaxUploadSizeExceededException): ResponseEntity<ErrorResponseDTO> {
        val errorCode: ErrorCode = ErrorCode.FILE_TOO_LARGE
        return ResponseEntity.status(errorCode.status).body(ErrorResponseDTO(errorCode.status, errorCode.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDTO> {
        val errorCode: ErrorCode = ErrorCode.BAD_TOKEN

        val bindingResult = ex.bindingResult

        val stringBuilder = StringBuilder()
        var sep = ""
        for (fieldError in bindingResult.fieldErrors) {
            stringBuilder.append(sep).append(fieldError.defaultMessage)
            sep = ", "
        }
        val message = stringBuilder.toString()

        return ResponseEntity.status(errorCode.status).body(ErrorResponseDTO(errorCode.status, message))
    }
}
