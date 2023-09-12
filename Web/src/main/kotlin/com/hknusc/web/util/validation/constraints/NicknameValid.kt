package com.hknusc.web.util.validation.constraints

import com.hknusc.web.util.validation.validator.NicknameValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NicknameValidator::class])
annotation class NicknameValid(
    val message: String = "올바른 형식의 닉네임이어야 합니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
