package com.hknusc.web.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class WebClientService(
    @param:Value("\${backEnd.webClientURL}") private val webClientURL: String,
) {
    private val webClient = WebClient.create(webClientURL)

    fun post(path: String?, body: Any) =
        webClient.post()
            .uri("/$path")
            .bodyValue(body)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
}
