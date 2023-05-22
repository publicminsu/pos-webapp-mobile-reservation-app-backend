package com.hknusc.web.dto
data class StoreDTO(
    var id: Int = 0,
    var accountId: Int,
    var name: String,
    var address: String?,
    var info: String?,
    var phoneNumber: String?,
    var canReservation: Boolean?,
    var operatingTime: String?,
    var isOpen: Boolean = false,
)
