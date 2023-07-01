package com.hknusc.web.service

import com.hknusc.web.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(private val reservationRepository: ReservationRepository) {
}