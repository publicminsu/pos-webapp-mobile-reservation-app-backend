package com.hknusc.web.service
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository
    fun getUsers()=userRepository.getUsers()
    fun saveUser(userDTO: UserDTO)=userRepository.saveUser(userDTO)
    fun getUser(id:Int)=userRepository.getUser(id)
    fun editUser(userDTO: UserDTO)=userRepository.editUser(userDTO)
    fun deleteUser(id:Int)=userRepository.deleteUser(id)
}