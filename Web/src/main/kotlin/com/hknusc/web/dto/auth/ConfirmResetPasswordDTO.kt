package com.hknusc.web.dto.auth

data class ConfirmResetPasswordDTO(val token: String, val password: String, val confirmPassword: String)
