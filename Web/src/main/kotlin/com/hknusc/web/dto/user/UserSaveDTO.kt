package com.hknusc.web.dto.user

import com.hknusc.web.util.PhotoUtility
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.multipart.MultipartFile

data class UserSaveDTO(
    val email: String,
    val password: String,
    val nickname: String,
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
