package com.hknusc.web.controller

import com.hknusc.web.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("check")
class CheckController(private val userService: UserService) {
    @PostMapping("email")
    fun checkEmail(email: String) = userService.checkEmail(email)

    //닉네임 중복 확인
    @PostMapping("nickname")
    fun checkNickname(nickname: String) = userService.checkNickname(nickname)

    //전화번호 중복 확인
    @PostMapping("phone-number")
    fun checkPhoneNumber(phoneNumber: String) = userService.checkPhoneNumber(phoneNumber)
}