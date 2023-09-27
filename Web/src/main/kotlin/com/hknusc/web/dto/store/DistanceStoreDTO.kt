package com.hknusc.web.dto.store

import com.hknusc.web.util.type.OperatingDay

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
    val operatingTime: List<OperatingDay>?,
    val isOpen: Boolean = false,
    val distance: Double,
)
