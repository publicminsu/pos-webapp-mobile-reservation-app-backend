package com.hknusc.web.dto

import org.springframework.http.HttpStatus

data class ErrorResponseDTO(
    val status: HttpStatus,
    val message: String?
)
