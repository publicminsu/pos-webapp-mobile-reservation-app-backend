package com.hknusc.web.dto.notification

import com.hknusc.web.util.type.NotificationStatus
import com.hknusc.web.util.type.NotificationType

data class NotificationDTO(
    var notificationType: NotificationType = NotificationType.TEST,
    var notificationStatus: NotificationStatus = NotificationStatus.INSERT,
    var typeId: Int = 0
)
