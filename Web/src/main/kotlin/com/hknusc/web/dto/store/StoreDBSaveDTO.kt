package com.hknusc.web.dto.store

import com.hknusc.web.util.type.OperatingDay

data class StoreDBSaveDTO(
    var id: Int = 0,
    val accountId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val isOpen: Boolean = false,
    val profilePhoto: String?,
    val photos: String
)
