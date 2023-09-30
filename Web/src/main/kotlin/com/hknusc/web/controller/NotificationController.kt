package com.hknusc.web.controller

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.service.NotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notifications")
class NotificationController(private val notificationService: NotificationService) {
    @GetMapping("subscribe", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@RequestAttribute userId: Int) = notificationService.subscribe(userId)

    @PostMapping
    fun notify(@RequestBody serverNotificationDTO: ServerNotificationDTO) =
        notificationService.notify(serverNotificationDTO)
}
