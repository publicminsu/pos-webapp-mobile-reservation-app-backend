package com.hknusc.web.dto.reservation

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.util.type.OrderCode
import com.hknusc.web.util.type.SSEEvent
import java.sql.Timestamp

data class ReservationDBEditDTO(
    var id: Int,
    var accountId: Int,
    var storeId: Int = 0,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode,
) {
    fun convertToServerNotification() =
        ServerNotificationDTO(
            targetSSEId = storeId,
            sseEvent = SSEEvent.RESERVATION_UPDATE,
            eventTargetId = id
        )
}
