package com.hknusc.web.controller

import com.hknusc.web.dto.store.DistanceDTO
import com.hknusc.web.service.TableOrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("table-orders")
class TableOrderController(private val tableOrderService: TableOrderService) {
    //좌표와 거리로 상점 가져오기
    @GetMapping("stores")
    fun getStoresByCoordinate(
        distanceDTO: DistanceDTO
    ) =
        tableOrderService.getStoresByCoordinate(distanceDTO)

    //테이블 아이디로 메뉴 가져오기
    @GetMapping("menus/{tableId}")
    fun getMenusByTableId(@PathVariable tableId: Int) = tableOrderService.getMenusByTableId(tableId)
}
