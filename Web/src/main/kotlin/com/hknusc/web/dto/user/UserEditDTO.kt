package com.hknusc.web.dto.user

import com.hknusc.web.util.PhotoUtility
import org.springframework.web.multipart.MultipartFile

data class UserEditDTO(
    var nickname: String,
    var phoneNumber: String,
    var paymentCard: String?,
    val profilePhoto: MultipartFile?
) {
    fun convertToUserDB(photoUtility: PhotoUtility, userId: Int) = UserDBEditDTO(
        id = userId,
        nickname = nickname,
        phoneNumber = phoneNumber,
        paymentCard = paymentCard,
        profilePhoto = photoUtility.saveImage(profilePhoto)
    )
}
