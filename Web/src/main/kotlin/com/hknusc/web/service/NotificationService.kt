package com.hknusc.web.service

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.repository.EmitterRepository
import com.hknusc.web.util.type.SSEEvent
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException

@Service
class NotificationService(private val emitterRepository: EmitterRepository) {
    private val sseExpireTime = 30 * 60 * 1000L//30분

    fun subscribe(id: Int): SseEmitter {
        val sseEmitter = createEmitter(id)

        //503 에러 방지를 위한 더미 데이터
        val dummy = ServerNotificationDTO(
            targetSSEId = id,
            sseEvent = SSEEvent.SERVER_CONNECT,
            eventTargetId = 0
        )
        sendToClient(dummy)

        return sseEmitter
    }

    fun notify(serverNotificationDTO: ServerNotificationDTO) {
        sendToClient(serverNotificationDTO)
    }

    private fun sendToClient(serverNotificationDTO: ServerNotificationDTO) {
        val targetSSEId = serverNotificationDTO.targetSSEId
        val sseEvent = serverNotificationDTO.sseEvent
        val eventTargetId = serverNotificationDTO.eventTargetId

        val sseEmitter = emitterRepository.get(targetSSEId)

        if (sseEmitter != null) {
            try {
                sseEmitter.send(
                    SseEmitter.event().id(targetSSEId.toString())
                        .name(sseEvent.name).data(eventTargetId)
                )
            } catch (e: IOException) {
                emitterRepository.deleteById(targetSSEId)
                sseEmitter.completeWithError(e)
            }
        }
    }

    private fun createEmitter(id: Int): SseEmitter {
        val sseEmitter = SseEmitter(sseExpireTime)
        emitterRepository.save(id, sseEmitter)
        return sseEmitter
    }
}
