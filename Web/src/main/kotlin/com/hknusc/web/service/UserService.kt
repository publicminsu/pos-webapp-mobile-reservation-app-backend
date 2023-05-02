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
    fun save(userDTO: UserDTO)=userRepository.save(userDTO)
}