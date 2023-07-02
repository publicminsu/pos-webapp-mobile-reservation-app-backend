package com.hknusc.web.service

import com.hknusc.web.dto.ReservationDTO
import com.hknusc.web.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(private val reservationRepository: ReservationRepository) {
    fun getReservations() = reservationRepository.getReservations()
    fun getReservation() = reservationRepository.getReservation()
    fun saveReservation(reservationDTO: ReservationDTO) = reservationRepository.saveReservation(reservationDTO)
}