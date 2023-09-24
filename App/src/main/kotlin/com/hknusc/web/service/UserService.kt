package com.hknusc.web.service

import com.hknusc.web.dto.user.*
import com.hknusc.web.repository.CheckRepository
import com.hknusc.web.repository.UserRepository
import com.hknusc.web.util.MailUtility
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTAuthenticationInfo
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UserService(
    @param:Value("\${frontEnd.emailURL}") private val emailURL: String,
    private val tokenProvider: JWTTokenProvider,
    private val mailUtility: MailUtility,
    private val photoUtility: PhotoUtility,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val checkRepository: CheckRepository
) {
    fun getUsers() = userRepository.getUsers()

    fun saveUser(userSaveDTO: UserSaveDTO) {
        val email: String = userSaveDTO.email
        val nickname: String = userSaveDTO.nickname
        val phoneNumber: String = userSaveDTO.phoneNumber

        checkEmailDuplicate(email, "")
        checkDuplicate(
            nickname, phoneNumber,
            "", ""
        )

        val userDBSaveDTO = userSaveDTO.convertToUserDB(photoUtility, passwordEncoder)

        try {
            userRepository.saveUser(userDBSaveDTO)

            val jwtAuthenticationInfo = JWTAuthenticationInfo(userDBSaveDTO.id, email)
            val token: String = tokenProvider.generateAccessToken(jwtAuthenticationInfo)

            mailUtility.send("이메일 인증", "링크: $emailURL$token", email)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.SIGNUP_FAIL)
        }
    }

    fun getUser(userId: Int): UserDTO {
        try {
            return userRepository.getUser(userId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }
    }

    fun getUserById(userId: Int): UserDTO {
        try {
            return userRepository.getUser(userId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }
    }

    fun getUserByIdList(userGetByIdListDTO: UserGetByIdListDTO) = userRepository.getUserByIdList(userGetByIdListDTO)

    fun editUser(userId: Int, userEditDTO: UserEditDTO) {
        val oldUser = userRepository.getUser(userId)!!

        val userNickname = userEditDTO.nickname
        val userPhoneNumber = userEditDTO.phoneNumber

        checkDuplicate(userNickname, userPhoneNumber, oldUser.nickname, oldUser.phoneNumber)

        oldUser.profilePhoto?.let { photoUtility.deleteImage(it) }

        val userDBEditDTO = userEditDTO.convertToUserDB(photoUtility, userId)

        try {
            userRepository.editUser(userDBEditDTO)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_EDIT_FAIL)
        }
    }

    fun getDeletedUser() = userRepository.getDeletedUsers()
    fun deleteUser(userId: Int) {
        lateinit var user: UserDTO
        try {
            user = userRepository.getUser(userId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        // 필요하다면 비밀번호 확인
        val curTime = Timestamp(System.currentTimeMillis())
        val deletedUser = DeletedUserSaveDTO(email = user.email, phoneNumber = user.phoneNumber, deleteTime = curTime)
        userRepository.saveDeletedUser(deletedUser)
        userRepository.deleteUser(userId)
    }

    private fun checkEmailDuplicate(email: String, oldEmail: String) {
        if ((oldEmail != email) && checkRepository.checkEmail(email)) {
            throw CustomException(ErrorCode.EMAIL_DUPLICATE)
        }
    }

    private fun checkDuplicate(
        nickname: String, phoneNumber: String,
        oldNickname: String, oldPhoneNumber: String
    ) {
        if ((oldNickname != nickname) && checkRepository.checkNickname(nickname)) {
            throw CustomException(ErrorCode.NICKNAME_DUPLICATE)
        } else if ((oldPhoneNumber != phoneNumber) && checkRepository.checkPhoneNumber(phoneNumber)) {
            throw CustomException(ErrorCode.PHONE_NUMBER_DUPLICATE)
        }
    }
}
