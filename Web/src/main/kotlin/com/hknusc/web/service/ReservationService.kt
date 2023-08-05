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
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        return reservationRepository.getReservations(userStoreId)
    }

    fun getReservation(bearerAccessToken: String, reservationId: Int): OrderDTO {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        return reservationRepository.getReservation(reservationId, userStoreId)
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

    fun editReservation(bearerAccessToken: String, reservationEditDTO: ReservationEditDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        val reservationDBEditDTO = ReservationDBEditDTO(
            reservationEditDTO.id,
            userStoreId,
            reservationEditDTO.tableId,
            reservationEditDTO.reservationTime,
            reservationEditDTO.orderCode
        )

        if (reservationRepository.editReservation(reservationDBEditDTO) == 0) {
            throw CustomException(ErrorCode.RESERVATION_NOT_FOUND)
        }
    }

    fun approveReservation(bearerAccessToken: String, reservationApproveDTO: ReservationApproveDTO) {
        val orderCode: OrderCode = reservationApproveDTO.orderCode
        val reservationDenyDetail: String? = reservationApproveDTO.reservationDenyDetail

        if (orderCode != OrderCode.RESERVATION && orderCode != OrderCode.RESERVATION_DENY) {
            throw CustomException(ErrorCode.RESERVATION_WRONG_CODE)
        }

        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        val reservationDBApproveDTO = ReservationDBApproveDTO(
            reservationApproveDTO.id,
            userStoreId,
            orderCode,
            reservationDenyDetail
        )

        if (reservationRepository.approveReservation(reservationDBApproveDTO) == 0) {
            throw CustomException(ErrorCode.RESERVATION_APPROVE_FAIL)
        }
    }
}