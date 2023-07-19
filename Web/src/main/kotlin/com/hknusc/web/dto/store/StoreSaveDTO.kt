package com.hknusc.web.dto.store

data class StoreSaveDTO(
    var name: String,
    var address: String?,
    var info: String?,
    var phoneNumber: String?,
    var canReservation: Boolean?,
    var operatingTime: String?,
)
