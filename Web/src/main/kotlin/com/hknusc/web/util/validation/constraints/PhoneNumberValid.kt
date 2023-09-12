package com.hknusc.web.util.validation.constraints

import com.hknusc.web.util.validation.validator.PhoneNumberValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PhoneNumberValidator::class])
annotation class PhoneNumberValid(
    val message: String = "올바른 형식의 전화번호이어야 합니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
