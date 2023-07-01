package com.hknusc.web.service

import com.hknusc.web.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class TableService(private val tableRepository: TableRepository) {
}