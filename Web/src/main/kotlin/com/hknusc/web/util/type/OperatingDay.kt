package com.hknusc.web.util.type

data class OperatingDay(
    var week: Int = 0,
    var operatingTimes: List<OperatingTime>? = null
)
