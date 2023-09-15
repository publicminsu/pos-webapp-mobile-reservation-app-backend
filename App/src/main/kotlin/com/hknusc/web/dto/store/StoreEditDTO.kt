package com.hknusc.web.dto.store

import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.validation.constraints.PhoneNumberValid
import org.springframework.web.multipart.MultipartFile

data class StoreEditDTO(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    @PhoneNumberValid
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
    val profilePhoto: MultipartFile?,
    val photos: List<MultipartFile>?
) {
    fun convertToStoreDB(photoUtility: PhotoUtility, storeId: Int) =
        StoreDBEditDTO(
            storeId,
            name,
            latitude,
            longitude,
            address,
            info,
            phoneNumber,
            canReservation,
            operatingTime,
            profilePhoto = photoUtility.saveImage(profilePhoto),
            photos = photoUtility.saveImagesAsString(photos)
        )
}
