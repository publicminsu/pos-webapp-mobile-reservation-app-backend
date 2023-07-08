package com.hknusc.web.repository

import com.hknusc.web.dto.ReservationApplyDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(): List<ReservationApplyDTO>
    fun getReservation(reservationId: Int): ReservationApplyDTO
    fun saveReservation(reservationApplyDTO: ReservationApplyDTO)
}