package com.hknusc.web.controller

import com.hknusc.web.dto.reservation.ReservationApproveDTO
import com.hknusc.web.dto.reservation.ReservationEditDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import com.hknusc.web.service.ReservationService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    // 영업자 기준이기에 가게 기준으로 해야한다. 무엇으로 값을 받을까
    // DB 값을 찾을 때 예약인 것만 가져온다.
    @GetMapping
    fun getReservations(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) =
        reservationService.getReservations(accessToken)

    @GetMapping("{reservationId}")
    fun getReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("reservationId") reservationId: Int
    ) = reservationService.getReservation(accessToken, reservationId)

    //예약 신청 (가게에서)
    @PostMapping
    fun saveReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        reservationSaveDTO: ReservationSaveDTO
    ) = reservationService.saveReservation(accessToken, reservationSaveDTO)

    @PatchMapping
    fun editReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        reservationEditDTO: ReservationEditDTO
    ) = reservationService.editReservation(accessToken, reservationEditDTO)

    //예약 처리.
    @PostMapping("approve")
    fun approveReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        reservationApproveDTO: ReservationApproveDTO
    ) = reservationService.approveReservation(accessToken, reservationApproveDTO)
}