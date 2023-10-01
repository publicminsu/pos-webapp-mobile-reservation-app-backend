package com.hknusc.web.controller

import com.hknusc.web.dto.reservation.ReservationApproveDTO
import com.hknusc.web.dto.reservation.ReservationEditDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import com.hknusc.web.service.ReservationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reservations")
class ReservationController(private val reservationService: ReservationService) {
    //예약 가져오기
    @GetMapping
    fun getReservations(@RequestAttribute userStoreId: Int) =
        reservationService.getReservations(userStoreId)

    //특정 예약 가져오기
    @GetMapping("{reservationId}")
    fun getReservation(
        @RequestAttribute userStoreId: Int,
        @PathVariable("reservationId") reservationId: Int
    ) = reservationService.getReservation(userStoreId, reservationId)

    @GetMapping("list")
    fun getReservationsByList(
        @RequestAttribute userStoreId: Int,
        @RequestParam(value = "id") reservationIdArray: List<Int>
    ) = reservationService.getReservationsByList(userStoreId, reservationIdArray)

    //예약 신청 (가게에서)
    @PostMapping
    fun saveReservation(
        @RequestAttribute userStoreId: Int,
        reservationSaveDTO: ReservationSaveDTO
    ) = reservationService.saveReservation(userStoreId, reservationSaveDTO)

    //예약 수정
    @PatchMapping
    fun editReservation(
        @RequestAttribute userStoreId: Int,
        reservationEditDTO: ReservationEditDTO
    ) = reservationService.editReservation(userStoreId, reservationEditDTO)

    //예약 처리.
    @PatchMapping("approve")
    fun approveReservation(
        @RequestAttribute userStoreId: Int,
        reservationApproveDTO: ReservationApproveDTO
    ) = reservationService.approveReservation(userStoreId, reservationApproveDTO)
}
