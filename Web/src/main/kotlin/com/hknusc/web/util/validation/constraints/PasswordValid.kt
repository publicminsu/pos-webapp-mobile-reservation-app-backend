package com.hknusc.web.util.validation.constraints

import com.hknusc.web.util.validation.validator.PasswordValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class PasswordValid(
    val message: String = "올바른 형식의 비밀번호이어야 합니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
