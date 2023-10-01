package com.hknusc.web.service

import com.hknusc.web.dto.notification.ServerNotificationDTO
import com.hknusc.web.dto.reservation.*
import com.hknusc.web.repository.ReservationRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.type.OrderCode
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val webClientService: WebClientService,
    private val reservationRepository: ReservationRepository
) {
    fun getReservations(userStoreId: Int) = reservationRepository.getReservations(userStoreId)

    fun getReservation(userStoreId: Int, reservationId: Int) = try {
        reservationRepository.getReservation(reservationId, userStoreId)!!
    } catch (e: Exception) {
        throw CustomException(ErrorCode.RESERVATION_NOT_FOUND)
    }


    fun saveReservation(userStoreId: Int, reservationSaveDTO: ReservationSaveDTO) {
        val reservationDBSaveDTO = ReservationDBSaveDTO(
            reservationSaveDTO.accountId,
            userStoreId,
            reservationSaveDTO.tableId,
            reservationSaveDTO.reservationTime,
            reservationSaveDTO.orderCode
        )

        reservationRepository.saveReservation(reservationDBSaveDTO)
    }

    fun editReservation(userStoreId: Int, reservationEditDTO: ReservationEditDTO) {
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

    fun approveReservation(userStoreId: Int, reservationApproveDTO: ReservationApproveDTO) {
        val orderCode: OrderCode = reservationApproveDTO.orderCode

        if (orderCode != OrderCode.RESERVATION && orderCode != OrderCode.RESERVATION_DENY) {
            throw CustomException(ErrorCode.RESERVATION_WRONG_CODE)
        }

        val reservationDBApproveDTO = reservationApproveDTO.convertToReservationDB(userStoreId)

        if (reservationRepository.approveReservation(reservationDBApproveDTO) == 0) {
            throw CustomException(ErrorCode.RESERVATION_APPROVE_FAIL)
        }

        val serverNotification = reservationDBApproveDTO.convertToServerNotification()
        postNotification(serverNotification)
    }

    private fun postNotification(serverNotification: ServerNotificationDTO) =
        webClientService.post("notifications", serverNotification)
}
