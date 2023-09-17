package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.ReservationDBEditDTO
import com.hknusc.web.dto.reservation.ReservationDBSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(userId: Int): List<OrderDTO>
    fun getReservationsByStore(userId: Int, storeId: Int): List<OrderDTO>
    fun saveReservation(reservationDBSaveDTO: ReservationDBSaveDTO)
    fun editReservation(reservationDBEditDTO: ReservationDBEditDTO): Int
}
