package com.hknusc.web.controller

import com.hknusc.web.dto.table.TableListSaveDTO
import com.hknusc.web.service.TableService
import com.hknusc.web.util.jwt.JwtTokenProvider
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    //개점된 가게에서 전체 테이블 가져오기
    @GetMapping
    fun getTables(@RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String) = tableService.getTables(accessToken)

    //특정 테이블 가져오기
    @GetMapping("{tableId}")
    fun getTable(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String, @PathVariable("tableId") tableId: Int
    ) = tableService.getTable(accessToken, tableId)

    //테이블 생성
    @PostMapping
    fun saveTable(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @Valid tableListSaveDTO: TableListSaveDTO
    ) = tableService.saveTable(accessToken, tableListSaveDTO)


    //테이블에 존재하는 주문 가져오기
    @GetMapping("orders")
    fun getTablesOrdersDetails(@RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String) =
        tableService.getTablesOrdersDetails(accessToken)
}
