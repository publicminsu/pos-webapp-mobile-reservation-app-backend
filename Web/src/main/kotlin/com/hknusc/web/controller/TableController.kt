package com.hknusc.web.controller

import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableEditDTO
import com.hknusc.web.dto.table.TableSaveDTO
import com.hknusc.web.service.TableService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    //개점된 가게에서 전체 테이블 가져오기
    @GetMapping
    fun getTables(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = tableService.getTables(accessToken)

    //특정 테이블 가져오기
    @GetMapping("{tableId}")
    fun getTable(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("tableId") tableId: Int
    ) = tableService.getTable(accessToken, tableId)

    //테이블 생성
    @PostMapping
    fun saveTable(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, tableSaveDTO: TableSaveDTO) =
        tableService.saveTable(accessToken, tableSaveDTO)

    //테이블 수정
    @PatchMapping
    fun editTable(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, tableEditDTO: TableEditDTO) =
        tableService.editTable(accessToken, tableEditDTO)

    //테이블은 예약이 사라질 때까지 지워지지 않는다.
    //테이블 삭제
    @DeleteMapping("{tableId}")
    fun deleteTable(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("tableId") tableId: Int
    ) = tableService.deleteTable(accessToken, tableId)

    @GetMapping("orders")
    fun getTablesOrdersDetails(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) =
        tableService.getTablesOrdersDetails(accessToken)
}