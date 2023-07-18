package com.hknusc.web.service

import com.hknusc.web.dto.user.DeletedUserDTO
import com.hknusc.web.dto.user.UserDTO
import com.hknusc.web.dto.user.UserSaveDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
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
    fun saveUser(userSaveDTO: UserSaveDTO) {
        userSaveDTO.password = passwordEncoder.encode(userSaveDTO.password)
        try {
            userRepository.saveUser(userSaveDTO)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.SIGNUP_FAIL)
        }
    }

    fun getUser(id: Int): UserDTO {
        try {
            return userRepository.getUser(id)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }
    }

    fun getUserByUserEmail(email: String) = userRepository.getUserByUserEmail(email)
    fun editUser(userDTO: UserDTO) = userRepository.editUser(userDTO)
    fun getDeletedUser() = userRepository.getDeletedUsers()
    fun deleteUser(bearerAccessToken: String) {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val userId = tokenProvider.findUserIdByJWT(accessToken)

        lateinit var user: UserDTO
        try {
            user = userRepository.getUser(userId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        // 필요하다면 비밀번호 확인
        val curTime = Timestamp(System.currentTimeMillis())
        val deletedUser = DeletedUserDTO(email = user.email, phoneNumber = user.phoneNumber, deleteTime = curTime)
        userRepository.saveDeletedUser(deletedUser)
        userRepository.deleteUser(userId)
    }
}