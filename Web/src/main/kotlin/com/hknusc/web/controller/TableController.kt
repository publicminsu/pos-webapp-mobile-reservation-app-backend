package com.hknusc.web.controller

import com.hknusc.web.service.TableService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
}