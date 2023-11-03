package com.hknusc.web.dto.store

import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.type.OperatingDay
import com.hknusc.web.util.type.StoreCategory

data class DistanceStoreDBDTO(
    val id: Int,
    val accountId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val isOpen: Boolean,
    val profilePhoto: String?,
    val photos: String,
    val storeCategory: StoreCategory,
    val distance: Double,
) {
    fun convertToStore(photoUtility: PhotoUtility, operatingDays: List<OperatingDay>?) = DistanceStoreDTO(
        id = id,
        accountId = accountId,
        name = name,
        latitude = latitude,
        longitude = longitude,
        address = address,
        info = info,
        phoneNumber = phoneNumber,
        canReservation = canReservation,
        operatingDays = operatingDays,
        isOpen = isOpen,
        profilePhoto = profilePhoto,
        photos = photoUtility.getImagesByString(photos),
        storeCategory = storeCategory,
        distance = distance
    )
}
