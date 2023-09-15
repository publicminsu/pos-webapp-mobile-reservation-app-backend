package com.hknusc.web.service

import com.hknusc.web.repository.CheckRepository
import org.springframework.stereotype.Service

@Service
class CheckService(private val checkRepository: CheckRepository) {
    fun checkEmail(email: String): Boolean = checkRepository.checkEmail(email)

    fun checkNickname(nickname: String): Boolean = checkRepository.checkNickname(nickname)

    fun checkPhoneNumber(phoneNumber: String): Boolean = checkRepository.checkPhoneNumber(phoneNumber)
}