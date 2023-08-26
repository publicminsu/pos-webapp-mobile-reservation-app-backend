package com.hknusc.web.dto.store

data class StoreEditDTO(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
    val isOpen: Boolean = false,
)
