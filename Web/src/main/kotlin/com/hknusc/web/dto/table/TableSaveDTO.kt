package com.hknusc.web.dto.table

data class TableSaveDTO(
    var name: String? = null,
    var coordX: String = "",
    var coordY: String = "",
    var width: String = "",
    var height: String = "",
    var privateKey: String = "",
)
