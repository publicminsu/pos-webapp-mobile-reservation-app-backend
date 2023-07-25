package com.hknusc.web.service

import com.hknusc.web.repository.CheckRepository
import org.springframework.stereotype.Service

@Service
class CheckService(private val checkRepository: CheckRepository) {
    fun checkEmail(email: String): Boolean = checkRepository.checkEmail(email) == 0

    fun checkNickname(nickname: String): Boolean = checkRepository.checkNickname(nickname) == 0

    fun checkPhoneNumber(phoneNumber: String): Boolean = checkRepository.checkPhoneNumber(phoneNumber) == 0
}