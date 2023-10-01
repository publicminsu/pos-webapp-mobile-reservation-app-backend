package com.hknusc.web.dto.reservation

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.util.type.OrderCode
import com.hknusc.web.util.type.SSEEvent

data class ReservationDBApproveDTO(
    var id: Int,
    var accountId: Int = 0,
    var storeId: Int,
    var orderCode: OrderCode,
    var reservationDenyDetail: String?
) {
    fun convertToServerNotification() =
        ServerNotificationDTO(
            targetSSEId = accountId,
            sseEvent = SSEEvent.RESERVATION_UPDATE,
            eventTargetId = id,
        )
}
