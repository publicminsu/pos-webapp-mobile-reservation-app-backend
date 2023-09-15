package com.hknusc.web.repository

import org.apache.ibatis.annotations.Mapper

@Mapper
interface CheckRepository {
    fun checkEmail(email: String): Boolean
    fun checkNickname(nickname: String): Boolean
    fun checkPhoneNumber(phoneNumber: String): Boolean
}