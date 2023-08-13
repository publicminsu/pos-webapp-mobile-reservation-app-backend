package com.hknusc.web.service

import com.hknusc.web.dto.user.*
import com.hknusc.web.repository.CheckRepository
import com.hknusc.web.repository.UserRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UserService(
    private val tokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val checkRepository: CheckRepository
) {
    fun getUsers() = userRepository.getUsers()

    fun saveUser(userSaveDTO: UserSaveDTO) {
        checkDuplicate(
            userSaveDTO.email, userSaveDTO.nickname, userSaveDTO.phoneNumber,
            "", "", ""
        )

        userSaveDTO.password = passwordEncoder.encode(userSaveDTO.password)

        try {
            userRepository.saveUser(userSaveDTO)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.SIGNUP_FAIL)
        }
    }

    fun getUser(bearerAccessToken: String): UserDTO {
        val userId = getUserId(bearerAccessToken)

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

    fun editUser(bearerAccessToken: String, userEditDTO: UserEditDTO) {
        val userId = getUserId(bearerAccessToken)

        val oldUser = userRepository.getUser(userId)!!

        val userEmail = userEditDTO.email
        val userNickname = userEditDTO.nickname
        val userPhoneNumber = userEditDTO.phoneNumber

        checkDuplicate(userEmail, userNickname, userPhoneNumber, oldUser.email, oldUser.nickname, oldUser.phoneNumber)

        val userDBEditDTO = UserDBEditDTO(
            userId,
            userEmail,
            userNickname,
            userPhoneNumber,
            userEditDTO.wishList,
            userEditDTO.couponList,
            userEditDTO.paymentCard
        )

        try {
            userRepository.editUser(userDBEditDTO)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_EDIT_FAIL)
        }
    }

    fun getDeletedUser() = userRepository.getDeletedUsers()
    fun deleteUser(bearerAccessToken: String) {
        val userId = getUserId(bearerAccessToken)

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

    private fun getUserId(bearerAccessToken: String): Int {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)
        return tokenProvider.findUserIdByJWT(accessToken)
    }

    private fun checkDuplicate(
        email: String, nickname: String, phoneNumber: String,
        oldEmail: String, oldNickname: String, oldPhoneNumber: String
    ) {
        if ((oldEmail != email) && checkRepository.checkEmail(email)) {
            throw CustomException(ErrorCode.EMAIL_DUPLICATE)
        } else if ((oldNickname != nickname) && checkRepository.checkNickname(nickname)) {
            throw CustomException(ErrorCode.NICKNAME_DUPLICATE)
        } else if ((oldPhoneNumber != phoneNumber) && checkRepository.checkPhoneNumber(phoneNumber)) {
            throw CustomException(ErrorCode.PHONE_NUMBER_DUPLICATE)
        }
    }
}