package com.hknusc.web.controller

import com.hknusc.web.dto.user.UserEditDTO
import com.hknusc.web.dto.user.UserGetByIdListDTO
import com.hknusc.web.dto.user.UserSaveDTO
import com.hknusc.web.service.UserService
import com.hknusc.web.util.jwt.JWTTokenProvider
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    //모든 사용자를 가져온다.
    @GetMapping("all")//계획상 사용할 일이 없다. 테스트용으로 두었다.
    fun getUsers() = userService.getUsers()

    //현재 사용자를 가져온다.
    @GetMapping
    fun getUser(@RequestAttribute userId: Int) = userService.getUser(userId)

    //특정 사용자를 가져온다.
    @GetMapping("{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Int
    ) = userService.getUserById(userId)

    //특정 사용자들을 가져온다.
    @PostMapping("all")
    fun getUserByIdList(userGetByIdListDTO: UserGetByIdListDTO) = userService.getUserByIdList(userGetByIdListDTO)

    //회원 가입 (이메일로 인증)
    @PostMapping
    fun saveUser(@Valid userSaveDTO: UserSaveDTO) = userService.saveUser(userSaveDTO)

    //회원 수정
    //이메일, 비밀번호는 쉽게 변경할 수 없어야 한다고 생각된다. 전화번호도 마찬가지이다. 중복된 번호가 입력되면 안된다. 하지만 전화번호는 허용해두었다.
    @PatchMapping
    fun editUser(@RequestAttribute userId: Int, @Valid userEditDTO: UserEditDTO) =
        userService.editUser(userId, userEditDTO)

    //삭제된 회원 가져오기
    @GetMapping("deletedUser") // 테스트용
    fun getDeletedUser() = userService.getDeletedUser()

    //회원 삭제
    @DeleteMapping//삭제 과정은 더 생각해봐야 할 요소, ex. 비밀번호 확인
    fun deleteUser(@RequestAttribute userId: Int) =
        userService.deleteUser(userId)
}
