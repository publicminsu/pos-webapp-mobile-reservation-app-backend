package com.hknusc.web.controller

import com.hknusc.web.dto.ReservationApplyDTO
import com.hknusc.web.service.ReservationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    // 영업자 기준이기에 가게 기준으로 해야한다. 무엇으로 값을 받을까
    // DB 값을 찾을 때 예약인 것만 가져온다.
    @GetMapping
    fun getReservations() = reservationService.getReservations()

    @GetMapping("{reservationId}")
    fun getReservation(@PathVariable("reservationId") reservationId: Int) =
        reservationService.getReservation(reservationId)

    //예약 신청 (가게에서)
    @PostMapping
    fun saveReservation(reservationApplyDTO: ReservationApplyDTO) =
        reservationService.saveReservation(reservationApplyDTO)
    //예약 처리.
}