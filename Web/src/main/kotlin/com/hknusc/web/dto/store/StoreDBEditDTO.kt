package com.hknusc.web.dto.store

import com.hknusc.web.util.type.OperatingDay

data class StoreDBEditDTO(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val profilePhoto: String?,
    val photos: String
)
