package com.hknusc.web.controller

import com.hknusc.web.dto.ReservationDTO
import com.hknusc.web.service.ReservationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    // 영업자 기준이기에 가게 기준으로 해야한다. 무엇으로 값을 받을까
    @GetMapping
    fun getReservations() = reservationService.getReservations()

    @GetMapping("{reservationId}")
    fun getReservation(@PathVariable("reservationId") reservationId: Int) = reservationService.getReservation(reservationId)

    @PostMapping
    fun saveReservation(reservationDTO: ReservationDTO) = reservationService.saveReservation(reservationDTO)
}