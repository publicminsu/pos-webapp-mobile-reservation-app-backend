package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.reservation.*
import com.hknusc.web.repository.ReservationRepository
import com.hknusc.web.util.type.OrderCode
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val tokenProvider: JwtTokenProvider,
    private val reservationRepository: ReservationRepository
) {
    fun getReservations(bearerAccessToken: String): List<OrderDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)
        return reservationRepository.getReservations(userId)
    }

    fun getReservationsByStore(bearerAccessToken: String, storeId: Int): List<OrderDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)
        return reservationRepository.getReservationsByStore(userId, storeId)
    }

    fun saveReservation(bearerAccessToken: String, reservationSaveDTO: ReservationSaveDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val reservationDBSaveDTO = reservationSaveDTO.convertToReservationDB(userId)
        reservationRepository.saveReservation(reservationDBSaveDTO)
    }

    fun editReservation(bearerAccessToken: String, reservationEditDTO: ReservationEditDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val reservationDBEditDTO = reservationEditDTO.convertToReservationDB(userId)
        if (reservationRepository.editReservation(reservationDBEditDTO) == 0) {
            throw CustomException(ErrorCode.RESERVATION_NOT_FOUND)
        }
    }
}
