package com.hknusc.web.controller

import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableSaveDTO
import com.hknusc.web.service.TableService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    @GetMapping
    fun getTables(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = tableService.getTables(accessToken)

    @GetMapping("{tableId}")
    fun getTable(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("tableId") tableId: Int
    ) = tableService.getTable(accessToken, tableId)

    //테이블 생성
    @PostMapping
    fun saveTable(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, tableSaveDTO: TableSaveDTO) =
        tableService.saveTable(accessToken, tableSaveDTO)

    @PatchMapping("{tableId}")
    fun editTable(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("tableId") tableDTO: TableDTO
    ) = tableService.editTable(accessToken, tableDTO)

    //테이블은 예약이 사라질 때까지 지워지지 않는다.
    @DeleteMapping("{tableId}")
    fun deleteTable(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("tableId") tableId: Int
    ) = tableService.deleteTable(accessToken, tableId)
}