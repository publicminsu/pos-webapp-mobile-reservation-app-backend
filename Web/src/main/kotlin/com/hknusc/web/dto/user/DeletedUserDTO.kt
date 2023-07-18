package com.hknusc.web.dto.user

import java.sql.Timestamp

data class DeletedUserDTO(
    var id: Int = 0,
    var email: String,
    var phoneNumber: String,
    var deleteTime: Timestamp,
)
