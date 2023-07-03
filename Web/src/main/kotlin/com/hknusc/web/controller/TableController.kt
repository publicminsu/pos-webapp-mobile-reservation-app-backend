package com.hknusc.web.controller

import com.hknusc.web.dto.TableDTO
import com.hknusc.web.service.TableService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    @GetMapping
    fun getTables() = tableService.getTables()

    @GetMapping("{tableId}")
    fun getTable(@PathVariable("tableId") tableId: Int) = tableService.getTable(tableId)

    //토큰으로 자격 확인
    @PostMapping
    fun saveTable(tableDTO: TableDTO) = tableService.saveTable(tableDTO)

    //토큰으로 자격 확인
    @PatchMapping("{tableId}")
    fun editTable(@PathVariable("tableId") tableDTO: TableDTO) = tableService.editTable(tableDTO)

    //토큰으로 자격 확인
    @DeleteMapping("{tableId}")
    fun deleteTable(@PathVariable("tableId") tableId: Int) = tableService.deleteTable(tableId)
}