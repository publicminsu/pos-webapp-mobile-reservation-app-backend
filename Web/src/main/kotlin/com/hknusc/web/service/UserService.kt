package com.hknusc.web.service

import com.hknusc.web.dto.DeletedUserDTO
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository
    fun getUsers() = userRepository.getUsers()
    fun saveUser(userDTO: UserDTO) = userRepository.saveUser(userDTO)
    fun getUser(id: Int) = userRepository.getUser(id)
    fun editUser(userDTO: UserDTO) = userRepository.editUser(userDTO)
    fun getDeletedUser() = userRepository.getDeletedUsers()
    fun deleteUser(id: Int) {
        var user = userRepository.getUser(id)

        // 필요하다면 비밀번호 확인
        var curTime= Timestamp(System.currentTimeMillis())
        var deletedUser=DeletedUserDTO(email = user.email, phoneNumber = user.phoneNumber, deleteTime =curTime)
        userRepository.saveDeletedUser(deletedUser)
        userRepository.deleteUser(id)
    }
}