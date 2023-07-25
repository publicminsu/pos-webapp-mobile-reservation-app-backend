package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.ReservationDBSaveDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(storeId: Int): List<OrderDTO>
    fun getReservation(reservationId: Int, storeId: Int): OrderDTO
    fun saveReservation(reservationDBSaveDTO: ReservationDBSaveDTO)
}