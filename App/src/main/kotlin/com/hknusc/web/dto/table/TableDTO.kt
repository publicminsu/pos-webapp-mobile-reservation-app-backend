package com.hknusc.web.dto.table

data class TableDTO(
    var id: Int,
    var storeId: Int,
    var name: String?,
    var coordX: Double,
    var coordY: Double,
    var width: Double,
    var height: Double,
    var privateKey: String,
)
