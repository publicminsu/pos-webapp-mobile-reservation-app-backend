package com.hknusc.web.service

import com.hknusc.web.dto.reservation.ReservationSaveDTO
import com.hknusc.web.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(private val reservationRepository: ReservationRepository) {
    fun getReservations() = reservationRepository.getReservations()
    fun getReservation(reservationId: Int) = reservationRepository.getReservation(reservationId)
    fun saveReservation(reservationSaveDTO: ReservationSaveDTO) =
        reservationRepository.saveReservation(reservationSaveDTO)
}