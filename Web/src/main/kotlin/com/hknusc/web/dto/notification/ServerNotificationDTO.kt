package com.hknusc.web.dto.notification

import com.hknusc.web.util.type.SSEEvent

data class ServerNotificationDTO(
    val targetSSEId: Int,
    var sseEvent: SSEEvent,
    var eventTargetId: Int
)
