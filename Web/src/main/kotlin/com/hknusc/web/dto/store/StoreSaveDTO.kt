package com.hknusc.web.dto.store

import java.awt.Point

data class StoreSaveDTO(
    val name: String,
    val coordinate: Point,
    val address: String?,
    val info: String?,
    val phoneNumber: String?,
    val canReservation: Boolean?,
    val operatingTime: String?,
)
