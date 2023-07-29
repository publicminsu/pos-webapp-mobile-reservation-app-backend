package com.hknusc.web.service

import com.hknusc.web.dto.reservation.ReservationApproveDTO
import com.hknusc.web.dto.reservation.ReservationDBApproveDTO
import com.hknusc.web.dto.reservation.ReservationDBSaveDTO
import com.hknusc.web.dto.reservation.ReservationSaveDTO
import com.hknusc.web.repository.ReservationRepository
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val tokenProvider: JwtTokenProvider,
    private val reservationRepository: ReservationRepository
) {
    fun getReservations(bearerAccessToken: String) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        reservationRepository.getReservations(userStoreId)
    }

    fun getReservation(bearerAccessToken: String, reservationId: Int) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        reservationRepository.getReservation(reservationId, userStoreId)
    }

    fun saveReservation(bearerAccessToken: String, reservationSaveDTO: ReservationSaveDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        val reservationDBSaveDTO = ReservationDBSaveDTO(
            reservationSaveDTO.accountId,
            userStoreId,
            reservationSaveDTO.tableId,
            reservationSaveDTO.reservationTime,
            reservationSaveDTO.orderCode
        )
        reservationRepository.saveReservation(reservationDBSaveDTO)
    }

    fun approveReservation(bearerAccessToken: String, reservationApproveDTO: ReservationApproveDTO) {
//        if (reservationApproveDTO.isReservation) {
//
//        }

//        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)
//
//        val reservationDBApproveDTO = ReservationDBApproveDTO(
//            reservationApproveDTO.id,
//            userStoreId,
//            reservationApproveDTO.isReservation,
//            reservationApproveDTO.reservationDenyDetail
//        )
//        if (reservationRepository.approveReservation(reservationDBApproveDTO) == 0) {
//        }
    }
}