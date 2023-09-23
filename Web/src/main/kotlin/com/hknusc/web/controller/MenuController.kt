package com.hknusc.web.controller

import com.hknusc.web.dto.menu.MenuEditDTO
import com.hknusc.web.dto.menu.MenuSaveDTO
import com.hknusc.web.util.jwt.JWTTokenProvider
import com.hknusc.web.service.MenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("menus")
class MenuController(private val menuService: MenuService) {
    //개점된 사용자가 자신의 가게의 메뉴 가져오기
    @GetMapping
    fun getMenus(@RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String) = menuService.getMenus(accessToken)

    //메뉴 가져오기
    @GetMapping("{menuId}")
    fun getMenu(@RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String, @PathVariable("menuId") menuId: Int) =
        menuService.getMenu(accessToken, menuId)

    //메뉴 저장하기
    @PostMapping
    fun saveMenu(@RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String, menuSaveDTO: MenuSaveDTO) =
        menuService.saveMenu(accessToken, menuSaveDTO)

    //메뉴 수정하기
    @PatchMapping
    fun editMenu(@RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String, menuEditDTO: MenuEditDTO) =
        menuService.editMenu(accessToken, menuEditDTO)

    //메뉴 삭제하기
    @DeleteMapping("{menuId}")
    fun deleteMenu(
        @RequestHeader(JWTTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable("menuId") menuId: Int
    ) = menuService.deleteMenu(accessToken, menuId)
}
