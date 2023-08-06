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
    //예약 가져오기
    @GetMapping
    fun getReservations(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) =
        reservationService.getReservations(accessToken)

    //특정 예약 가져오기
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

    //예약 수정
    @PatchMapping
    fun editReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        reservationEditDTO: ReservationEditDTO
    ) = reservationService.editReservation(accessToken, reservationEditDTO)

    //예약 처리.
    @PatchMapping("approve")
    fun approveReservation(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        reservationApproveDTO: ReservationApproveDTO
    ) = reservationService.approveReservation(accessToken, reservationApproveDTO)
}