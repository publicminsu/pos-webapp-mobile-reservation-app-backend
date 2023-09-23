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
    fun getReservations(@RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String) =
        reservationService.getReservations(accessToken)

    //특정 상점 예약 가져오기
    @GetMapping("{storeId}")
    fun getReservationsByStore(
        @RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable storeId: Int
    ) = reservationService.getReservationsByStore(accessToken, storeId)

    //예약 신청 (가게에서)
    @PostMapping
    fun saveReservation(
        @RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String,
        reservationSaveDTO: ReservationSaveDTO
    ) = reservationService.saveReservation(accessToken, reservationSaveDTO)

    //예약 수정
    @PatchMapping
    fun editReservation(
        @RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String,
        reservationEditDTO: ReservationEditDTO
    ) = reservationService.editReservation(accessToken, reservationEditDTO)
}
