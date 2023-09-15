package com.hknusc.web.dto.table

import jakarta.validation.constraints.PositiveOrZero

data class TableSaveDTO(
    var name: String? = null,
    var coordX: Double = 0.0,
    var coordY: Double = 0.0,
    @PositiveOrZero
    var width: Double = 0.0,
    @PositiveOrZero
    var height: Double = 0.0,
    var privateKey: String = "",
)
