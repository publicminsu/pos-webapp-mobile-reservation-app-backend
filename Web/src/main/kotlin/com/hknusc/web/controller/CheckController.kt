package com.hknusc.web.controller

import com.hknusc.web.service.CheckService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("check")
class CheckController(private val checkService: CheckService) {
    //이메일 중복 확인
    @PostMapping("email")
    fun checkEmail(email: String) = checkService.checkEmail(email)

    //닉네임 중복 확인
    @PostMapping("nickname")
    fun checkNickname(nickname: String) = checkService.checkNickname(nickname)

    //전화번호 중복 확인
    @PostMapping("phone-number")
    fun checkPhoneNumber(phoneNumber: String) = checkService.checkPhoneNumber(phoneNumber)
}