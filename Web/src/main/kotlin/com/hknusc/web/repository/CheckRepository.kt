package com.hknusc.web.repository

import org.apache.ibatis.annotations.Mapper

@Mapper
interface CheckRepository {
    fun checkEmail(email: String): Int
    fun checkNickname(nickname: String): Int
    fun checkPhoneNumber(phoneNumber: String): Int
}