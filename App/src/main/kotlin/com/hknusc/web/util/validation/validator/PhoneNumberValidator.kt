package com.hknusc.web.util.validation.validator

import com.hknusc.web.util.validation.constraints.PhoneNumberValid
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class PhoneNumberValidator : ConstraintValidator<PhoneNumberValid, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }

        val pattern = Pattern.compile("^01([0-1|6-9])-?\\d{3,4}-?\\d{4}$")
        return pattern.matcher(value).find()
    }
}
