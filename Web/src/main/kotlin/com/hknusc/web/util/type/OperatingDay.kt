package com.hknusc.web.util.type

import org.jetbrains.annotations.NotNull

data class OperatingDay(
    var week: Int = 0,
    var operatingTimes: List<OperatingTime>? = null
)
