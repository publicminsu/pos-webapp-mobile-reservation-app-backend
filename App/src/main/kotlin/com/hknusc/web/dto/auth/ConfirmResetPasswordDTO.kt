package com.hknusc.web.dto.auth

import com.hknusc.web.util.validation.constraints.PasswordValid

data class ConfirmResetPasswordDTO(
    val token: String,
    @PasswordValid val password: String,
    @PasswordValid val confirmPassword: String
)
