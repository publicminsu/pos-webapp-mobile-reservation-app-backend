package com.hknusc.web.controller

import com.hknusc.web.service.ReservationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
}