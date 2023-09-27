package com.hknusc.web.util.type

import java.time.LocalTime

data class OperatingTime(
    var openTime: LocalTime = LocalTime.MIN,
    var closeTime: LocalTime = LocalTime.MAX
)
