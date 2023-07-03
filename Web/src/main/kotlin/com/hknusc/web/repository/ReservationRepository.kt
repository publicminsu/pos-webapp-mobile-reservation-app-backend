package com.hknusc.web.repository

import com.hknusc.web.dto.ReservationDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(): List<ReservationDTO>
    fun getReservation(reservationId: Int): ReservationDTO
    fun saveReservation(reservationDTO: ReservationDTO)
}