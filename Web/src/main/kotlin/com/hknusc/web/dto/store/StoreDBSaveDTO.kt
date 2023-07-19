package com.hknusc.web.dto.store

data class StoreDBSaveDTO(
    var accountId: Int,
    var name: String,
    var address: String?,
    var info: String?,
    var phoneNumber: String?,
    var canReservation: Boolean?,
    var operatingTime: String?,
    var isOpen: Boolean = false,
)
