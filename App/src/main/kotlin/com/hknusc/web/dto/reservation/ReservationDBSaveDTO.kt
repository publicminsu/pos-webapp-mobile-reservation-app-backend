package com.hknusc.web.dto.reservation

import com.hknusc.web.dto.notification.NotificationDTO
import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.util.type.OrderCode
import com.hknusc.web.util.type.SSEEvent
import java.sql.Timestamp

data class ReservationDBSaveDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode,
) {
    fun convertToServerNotification() =
        ServerNotificationDTO(
            targetSSEId = storeId,
            notificationDTO = NotificationDTO(SSEEvent.RESERVATION_INSERT, id)
        )
}
