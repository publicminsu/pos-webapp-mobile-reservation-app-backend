package com.hknusc.web.util.validation.validator

import com.hknusc.web.util.validation.constraints.NicknameValid
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class NicknameValidator : ConstraintValidator<NicknameValid, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }

        val pattern = Pattern.compile("[a-zA-Z0-9]{5,20}")
        return pattern.matcher(value).find()
    }
}
