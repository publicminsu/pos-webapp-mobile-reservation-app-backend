package com.hknusc.web.controller
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController {
    //비밀번호의 암호화 필요
    @Autowired
    lateinit var userService: UserService
    @GetMapping //계획상 사용할 일이 없다. 테스트용으로 두었다.
    fun getUsers()=userService.getUsers()
    @PostMapping
    fun saveUser(userDTO: UserDTO)=userService.saveUser(userDTO)
    @GetMapping("{id}")
    fun getUser(@PathVariable("id") id:Int)=userService.getUser(id)
    @PatchMapping("{id}")
    fun editUser(@PathVariable("id") id:Int,userDTO: UserDTO)=userService.editUser(userDTO)
    @DeleteMapping("{id}") //삭제하는 것이 맞는가?
    fun deleteUser(@PathVariable("id") id:Int)=userService.deleteUser(id)
}