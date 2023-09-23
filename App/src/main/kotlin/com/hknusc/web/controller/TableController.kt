package com.hknusc.web.controller

import com.hknusc.web.service.TableService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tables")
class TableController(private val tableService: TableService) {
    //테이블 결제는 어떻게?
    //개점된 가게에서 전체 테이블 가져오기
    @GetMapping("{storeId}")
    fun getTables(@PathVariable storeId: Int) = tableService.getTables(storeId)

    //테이블에 존재하는 주문 가져오기
    @GetMapping("orders/{storeId}")
    fun getTablesOrdersDetails(@PathVariable storeId: Int) =
        tableService.getTablesOrdersDetails(storeId)
}
