package com.hknusc.web.controller

import com.hknusc.web.dto.menu.MenuEditDTO
import com.hknusc.web.dto.menu.MenuSaveDTO
import com.hknusc.web.service.MenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("menus")
class MenuController(private val menuService: MenuService) {
    //개점된 사용자가 자신의 가게의 메뉴 가져오기
    @GetMapping
    fun getMenus(@RequestAttribute userStoreId: Int) = menuService.getMenus(userStoreId)

    //메뉴 가져오기
    @GetMapping("{menuId}")
    fun getMenu(@RequestAttribute userStoreId: Int, @PathVariable("menuId") menuId: Int) =
        menuService.getMenu(userStoreId, menuId)

    //메뉴 저장하기
    @PostMapping
    fun saveMenu(@RequestAttribute userStoreId: Int, menuSaveDTO: MenuSaveDTO) =
        menuService.saveMenu(userStoreId, menuSaveDTO)

    //메뉴 수정하기
    @PatchMapping
    fun editMenu(@RequestAttribute userStoreId: Int, menuEditDTO: MenuEditDTO) =
        menuService.editMenu(userStoreId, menuEditDTO)

    //메뉴 삭제하기
    @DeleteMapping("{menuId}")
    fun deleteMenu(
        @RequestAttribute userStoreId: Int,
        @PathVariable("menuId") menuId: Int
    ) = menuService.deleteMenu(userStoreId, menuId)
}
