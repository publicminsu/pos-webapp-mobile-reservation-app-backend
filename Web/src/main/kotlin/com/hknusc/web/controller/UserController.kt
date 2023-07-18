package com.hknusc.web.controller

import com.hknusc.web.dto.user.UserDTO
import com.hknusc.web.dto.user.UserEditDTO
import com.hknusc.web.dto.user.UserSaveDTO
import com.hknusc.web.service.UserService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    @GetMapping ("all")//계획상 사용할 일이 없다. 테스트용으로 두었다.
    fun getUsers() = userService.getUsers()

    @GetMapping
    fun getUser(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = userService.getUser(accessToken)

    @PostMapping
    fun saveUser(userSaveDTO: UserSaveDTO) = userService.saveUser(userSaveDTO)

    //이메일, 비밀번호는 쉽게 변경할 수 없어야 한다고 생각된다. 전화번호도 마찬가지이다. 중복된 번호가 입력되면 안된다. 하지만 전화번호는 허용해두었다.
    @PatchMapping
    fun editUser(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        userEditDTO: UserEditDTO
    ) = userService.editUser(accessToken, userEditDTO)

    @GetMapping("deletedUser") // 테스트용
    fun getDeletedUser() = userService.getDeletedUser()

    @DeleteMapping() //삭제 과정은 더 생각해봐야 할 요소, ex. 비밀번호 확인
    fun deleteUser(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) =
        userService.deleteUser(accessToken)
}