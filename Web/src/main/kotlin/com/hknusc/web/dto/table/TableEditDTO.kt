package com.hknusc.web.dto.table

data class TableEditDTO(
    var id: Int,
    var name: String?,
    var coordX: String,
    var coordY: String,
    var width: String,
    var height: String,
    var privateKey: String,
)
