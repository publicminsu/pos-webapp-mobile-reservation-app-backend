package com.hknusc.web.dto.store

data class StoreDTO(
    val id: Int,
    val accountId: Int,
    val name: String,
    var latitude: Double,
    var longitude: Double,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
    val isOpen: Boolean = false,
)
