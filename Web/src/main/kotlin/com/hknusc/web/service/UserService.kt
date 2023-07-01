package com.hknusc.web.service

import com.hknusc.web.dto.DeletedUserDTO
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UserService(
    private val tokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
) {
    fun getUsers() = userRepository.getUsers()
    fun saveUser(userDTO: UserDTO) {
        userDTO.password = passwordEncoder.encode(userDTO.password)
        userRepository.saveUser(userDTO)
    }

    fun getUser(id: Int) = userRepository.getUser(id)
    fun getUserByUserEmail(email: String) = userRepository.getUserByUserEmail(email)
    fun editUser(userDTO: UserDTO) = userRepository.editUser(userDTO)
    fun getDeletedUser() = userRepository.getDeletedUsers()
    fun deleteUser(bearerAccessToken: String) {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken);

        tokenProvider.validateToken(accessToken.toString())

        val userId = tokenProvider.findUserIdByJWT(accessToken);
        val user = userRepository.getUser(userId)
        // 필요하다면 비밀번호 확인
        val curTime = Timestamp(System.currentTimeMillis())
        val deletedUser = DeletedUserDTO(email = user.email, phoneNumber = user.phoneNumber, deleteTime = curTime)
        userRepository.saveDeletedUser(deletedUser)
        userRepository.deleteUser(userId)
    }
}