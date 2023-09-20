package com.hknusc.web.dto.user

import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.validation.constraints.NicknameValid
import com.hknusc.web.util.validation.constraints.PasswordValid
import com.hknusc.web.util.validation.constraints.PhoneNumberValid
import jakarta.validation.constraints.Email
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.multipart.MultipartFile

data class UserSaveDTO(
    @field:Email
    val email: String,
    @PasswordValid
    val password: String,
    @NicknameValid
    val nickname: String,
    @PhoneNumberValid
    val phoneNumber: String,
    val profilePhoto: MultipartFile?
) {
    fun convertToUserDB(photoUtility: PhotoUtility, passwordEncoder: PasswordEncoder) =
        UserDBSaveDTO(
            email = email,
            password = passwordEncoder.encode(password),
            nickname = nickname,
            phoneNumber = phoneNumber,
            profilePhoto = photoUtility.saveImage(profilePhoto)
        )
}
