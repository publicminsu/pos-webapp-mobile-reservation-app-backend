package com.hknusc.web.dto.store

import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.type.OperatingDay
import com.hknusc.web.util.type.StoreCategory
import com.hknusc.web.util.validation.constraints.PhoneNumberValid
import org.springframework.web.multipart.MultipartFile

data class StoreEditDTO(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    var operatingDays: List<OperatingDay>?,
    val profilePhoto: MultipartFile?,
    val photos: List<MultipartFile>?,
    val storeCategory: StoreCategory,
) {
    fun convertToStoreDB(photoUtility: PhotoUtility, storeId: Int) =
        StoreDBEditDTO(
            id = storeId,
            name = name,
            latitude = latitude,
            longitude = longitude,
            address = address,
            info = info,
            phoneNumber = phoneNumber,
            canReservation = canReservation,
            profilePhoto = photoUtility.saveImage(profilePhoto),
            photos = photoUtility.saveImagesAsString(photos),
            storeCategory = storeCategory
        )
}
