package com.hknusc.web.service

import com.hknusc.web.dto.notification.NotificationDTO
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
        val dummy = NotificationDTO(SSEEvent.SERVER_CONNECT, 0)
        sendToClient(id, dummy)

        return sseEmitter
    }

    fun notify(serverNotificationDTO: ServerNotificationDTO) {
        val userId = serverNotificationDTO.targetSSEId
        val data = try {
            serverNotificationDTO.notificationDTO!!
        } catch (e: Exception) {
            //비어있는 경우
            return
        }
        sendToClient(userId, data)
    }

    private fun sendToClient(id: Int, data: NotificationDTO) {
        val sseEmitter = emitterRepository.get(id)

        if (sseEmitter != null) {
            try {
                sseEmitter.send(
                    SseEmitter.event().id(id.toString())
                        .name(data.sseEvent.name).data(data.typeId)
                )
            } catch (e: IOException) {
                emitterRepository.deleteById(id)
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
