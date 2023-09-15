package com.hknusc.web.dto.user

import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.validation.constraints.NicknameValid
import com.hknusc.web.util.validation.constraints.PhoneNumberValid
import org.springframework.web.multipart.MultipartFile

data class UserEditDTO(
    @NicknameValid
    var nickname: String,
    @PhoneNumberValid
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
