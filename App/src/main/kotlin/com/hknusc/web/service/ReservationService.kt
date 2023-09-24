package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.*
import com.hknusc.web.repository.ReservationRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val tokenProvider: JWTTokenProvider,
    private val reservationRepository: ReservationRepository
) {
    fun getReservations(userId: Int) = reservationRepository.getReservations(userId)

    fun getReservationsByStore(userId: Int, storeId: Int) =
        reservationRepository.getReservationsByStore(userId, storeId)

    fun saveReservation(userId: Int, reservationSaveDTO: ReservationSaveDTO) {
        val reservationDBSaveDTO = reservationSaveDTO.convertToReservationDB(userId)
        reservationRepository.saveReservation(reservationDBSaveDTO)
    }

    fun editReservation(userId: Int, reservationEditDTO: ReservationEditDTO) {
        val reservationDBEditDTO = reservationEditDTO.convertToReservationDB(userId)
        if (reservationRepository.editReservation(reservationDBEditDTO) == 0) {
            throw CustomException(ErrorCode.RESERVATION_NOT_FOUND)
        }
    }
}
