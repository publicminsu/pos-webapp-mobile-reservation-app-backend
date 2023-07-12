package com.hknusc.web.controller

import com.hknusc.web.dto.MenuDTO
import com.hknusc.web.dto.MenuEditDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.service.MenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("menus")
class MenuController(private val menuService: MenuService) {
    @GetMapping
    fun getMenus(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = menuService.getMenus(accessToken)

    @GetMapping("{menuId}")
    fun getMenu(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, @PathVariable("menuId") menuId: Int) =
        menuService.getMenu(accessToken, menuId)

    @PostMapping
    fun saveMenu(menuDTO: MenuDTO) = menuService.saveMenu(menuDTO)

    @PatchMapping
    fun editMenu(menuEditDTO: MenuEditDTO) = menuService.editMenu(menuEditDTO)

    @DeleteMapping("{menuId}")
    fun deleteMenu(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("menuId") menuId: Int
    ) = menuService.deleteMenu(accessToken, menuId)
}