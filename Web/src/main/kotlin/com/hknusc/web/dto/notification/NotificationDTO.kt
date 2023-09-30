package com.hknusc.web.dto.notification

import com.hknusc.web.util.type.SSEEvent

data class NotificationDTO(
    var sseEvent: SSEEvent = SSEEvent.SERVER_CONNECT,
    var typeId: Int = 0
)
