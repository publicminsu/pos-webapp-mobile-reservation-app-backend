package com.hknusc.web.dto.reservation

import com.hknusc.web.dto.notification.NotificationDTO
import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.util.type.NotificationStatus
import com.hknusc.web.util.type.NotificationType
import com.hknusc.web.util.type.OrderCode
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
            notificationDTO = NotificationDTO(NotificationType.RESERVATION, NotificationStatus.INSERT, id)
        )
}
