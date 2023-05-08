package com.hknusc.web.dto
import java.sql.Time
data class StoreDTO(
    var id:Int=0,
    var accountId:Int,
    var name:String,
    var openTime:Time?,
    var closeTime:Time?,
    var address:String?,
    var info:String?,
    var phoneNumber:String?,
    var canReservation:Boolean?,
)
