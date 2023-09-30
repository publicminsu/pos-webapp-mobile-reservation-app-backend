package com.hknusc.web.controller

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.service.NotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notifications")
class NotificationController(private val notificationService: NotificationService) {
    @GetMapping("subscribe/{id}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@PathVariable id: Int) = notificationService.subscribe(id)

    @PostMapping
    fun notify(@RequestBody serverNotificationDTO: ServerNotificationDTO) =
        notificationService.notify(serverNotificationDTO)
}
