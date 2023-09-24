package com.hknusc.web.controller

import com.hknusc.web.dto.table.TableListSaveDTO
import com.hknusc.web.service.TableService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    //개점된 가게에서 전체 테이블 가져오기
    @GetMapping
    fun getTables(@RequestAttribute userStoreId: Int) = tableService.getTables(userStoreId)

    //특정 테이블 가져오기
    @GetMapping("{tableId}")
    fun getTable(
        @RequestAttribute userStoreId: Int, @PathVariable("tableId") tableId: Int
    ) = tableService.getTable(userStoreId, tableId)

    //테이블 생성
    @PostMapping
    fun saveTable(
        @RequestAttribute userStoreId: Int,
        @Valid tableListSaveDTO: TableListSaveDTO
    ) = tableService.saveTable(userStoreId, tableListSaveDTO)


    //테이블에 존재하는 주문 가져오기
    @GetMapping("orders")
    fun getTablesOrdersDetails(@RequestAttribute userStoreId: Int) =
        tableService.getTablesOrdersDetails(userStoreId)
}
