package com.hknusc.web.dto.table

data class TableDTO(
    var id: Int = 0,
    var storeId: Int,
    var name: String?,
    var coordX: String,
    var coordY: String,
    var width: String,
    var height: String,
    var privateKey: String,
)
