package com.hknusc.web.controller

import com.hknusc.web.dto.ReservationDTO
import com.hknusc.web.service.ReservationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    @GetMapping
    fun getReservations() = reservationService.getReservations()

    @GetMapping("{reservationId}")
    fun getReservation(@PathVariable("reservationId") reservationId: Int) = reservationService.getReservation()

    @PostMapping
    fun saveReservation(reservationDTO: ReservationDTO) = reservationService.saveReservation(reservationDTO)
}