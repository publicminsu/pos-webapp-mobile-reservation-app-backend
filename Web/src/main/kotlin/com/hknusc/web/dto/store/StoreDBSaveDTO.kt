package com.hknusc.web.dto.store

import java.awt.Point

data class StoreDBSaveDTO(
    val accountId: Int,
    val name: String,
    val coordinate: Point,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
    val isOpen: Boolean = false,
)
