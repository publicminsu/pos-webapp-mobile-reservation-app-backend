package com.hknusc.web.util.validation.validator

import com.hknusc.web.util.validation.constraints.PasswordValid
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class PasswordValidator : ConstraintValidator<PasswordValid, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }

        val pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%^&*()])[a-zA-Z\\d!@#\$%^&*()]{8,}\$")
        return pattern.matcher(value).find()
    }
}
