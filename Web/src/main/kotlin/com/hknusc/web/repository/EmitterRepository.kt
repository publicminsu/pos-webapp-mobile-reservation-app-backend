package com.hknusc.web.repository

import org.springframework.stereotype.Repository
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap


@Repository
class EmitterRepository(private val sseEmitterMap: Map<Int, SseEmitter> = ConcurrentHashMap()) {
    fun save(id: Int, sseEmitter: SseEmitter) = sseEmitterMap.plus(Pair(id, sseEmitter))

    fun deleteById(id: Int) = sseEmitterMap.minus(id)

    fun get(id: Int) = sseEmitterMap[id]
}
