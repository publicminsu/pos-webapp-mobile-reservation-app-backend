package com.hknusc.web.dto.store

data class StoreSaveDTO(
    val name: String,
    var latitude: Double,
    var longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
)
