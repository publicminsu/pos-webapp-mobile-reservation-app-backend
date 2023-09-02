package com.hknusc.web.dto.store

import com.hknusc.web.util.PhotoUtility

data class StoreDBDTO(
    val id: Int,
    val accountId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
    val isOpen: Boolean,
    val profilePhoto: String?,
    val photos: String
) {
    fun convertToStore(photoUtility: PhotoUtility) = StoreDTO(
        id = id,
        accountId = accountId,
        name = name,
        latitude = latitude,
        longitude = longitude,
        address = address,
        info = info,
        phoneNumber = phoneNumber,
        canReservation = canReservation,
        operatingTime = operatingTime,
        profilePhoto = profilePhoto,
        isOpen = isOpen,
        photos = photoUtility.getImagesByString(photos)
    )
}

