package com.hknusc.web.dto.store

import com.hknusc.web.util.type.OperatingDay
import com.hknusc.web.util.type.StoreCategory

data class DistanceStoreDTO(
    val id: Int,
    val accountId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingDays: List<OperatingDay>?,
    val isOpen: Boolean = false,
    val profilePhoto: String?,
    val photos: List<String>,
    val storeCategory: StoreCategory,
    val distance: Double,
)
