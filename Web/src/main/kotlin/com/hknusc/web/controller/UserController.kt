package com.hknusc.web.controller
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController {
    @Autowired
    lateinit var userService: UserService
    @GetMapping
    fun getUsers()=userService.getUsers()
    @PostMapping
    fun save(userDTO: UserDTO)=userService.save(userDTO)

}