package com.hknusc.web.controller

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.service.NotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notifications")
class NotificationController(private val notificationService: NotificationService) {
    //상점 번호 (개점하지 않아도 연결은 되는데 0번이라 들어오는 값은 없음)
    @GetMapping("subscribe", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@RequestAttribute userStoreId: Int) = notificationService.subscribe(userStoreId)
    
    @PostMapping
    fun notify(@RequestBody serverNotificationDTO: ServerNotificationDTO) =
        notificationService.notify(serverNotificationDTO)
}
