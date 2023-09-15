package com.hknusc.web.controller

import com.hknusc.web.service.MenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menus")
class MenuController(private val menuService: MenuService) {
    //개점된 사용자가 자신의 가게의 메뉴 가져오기
    @GetMapping("{storeId}")
    fun getMenus(@PathVariable storeId: Int) = menuService.getMenus(storeId)

    //메뉴 가져오기
    @GetMapping("{storeId}/{menuId}")
    fun getMenu(@PathVariable storeId: Int, @PathVariable menuId: Int) =
        menuService.getMenu(storeId, menuId)
}
