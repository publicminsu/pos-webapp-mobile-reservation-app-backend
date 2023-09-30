package com.hknusc.web.repository

import org.springframework.stereotype.Repository
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap


@Repository
class EmitterRepository {
    private val emitters: MutableMap<Int, SseEmitter> = ConcurrentHashMap()
    fun save(id: Int, sseEmitter: SseEmitter): SseEmitter {
        emitters[id] = sseEmitter
        sseEmitter.onCompletion { emitters.remove(id) }
        sseEmitter.onTimeout { emitters.remove(id) }
        return sseEmitter
    }

    fun deleteById(id: Int) = emitters.remove(id)

    fun get(id: Int) = emitters[id]
}
