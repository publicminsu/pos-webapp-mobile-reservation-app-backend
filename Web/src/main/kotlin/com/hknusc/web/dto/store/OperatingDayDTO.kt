package com.hknusc.web.dto.store

import com.hknusc.web.util.type.OperatingDay

data class OperatingDayDTO(val userStoreId: Int, val operatingDays: List<OperatingDay>?)
