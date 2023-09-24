package com.hknusc.web.controller

import com.hknusc.web.dto.reservation.ReservationEditDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import com.hknusc.web.service.ReservationService
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    //예약 가져오기
    @GetMapping
    fun getReservations(@RequestAttribute userId: Int) =
        reservationService.getReservations(userId)

    //특정 상점 예약 가져오기
    @GetMapping("{storeId}")
    fun getReservationsByStore(
        @RequestAttribute userId: Int,
        @PathVariable storeId: Int
    ) = reservationService.getReservationsByStore(userId, storeId)

    //예약 신청 (가게에서)
    @PostMapping
    fun saveReservation(
        @RequestAttribute userId: Int,
        reservationSaveDTO: ReservationSaveDTO
    ) = reservationService.saveReservation(userId, reservationSaveDTO)

    //예약 수정
    @PatchMapping
    fun editReservation(
        @RequestAttribute userId: Int,
        reservationEditDTO: ReservationEditDTO
    ) = reservationService.editReservation(userId, reservationEditDTO)
}
