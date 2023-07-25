package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(): List<OrderDTO>
    fun getReservation(reservationId: Int): OrderDTO
    fun saveReservation(reservationSaveDTO: ReservationSaveDTO)
}