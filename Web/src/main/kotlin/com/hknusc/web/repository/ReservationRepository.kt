package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.ReservationDBApproveDTO
import com.hknusc.web.dto.reservation.ReservationDBEditDTO
import com.hknusc.web.dto.reservation.ReservationEditDTO
import com.hknusc.web.dto.reservation.ReservationDBSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReservationRepository {
    fun getReservations(storeId: Int): List<OrderDTO>
    fun getReservation(reservationId: Int, storeId: Int): OrderDTO?
    fun getReservationsByList(storeId: Int, reservationIdArray: List<Int>): List<OrderDTO>
    fun saveReservation(reservationDBSaveDTO: ReservationDBSaveDTO)
    fun editReservation(reservationDBEditDTO: ReservationDBEditDTO): Int
    fun approveReservation(reservationDBApproveDTO: ReservationDBApproveDTO): Int
}
