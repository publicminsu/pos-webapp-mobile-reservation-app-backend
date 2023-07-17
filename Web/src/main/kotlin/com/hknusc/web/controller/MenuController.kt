package com.hknusc.web.controller

import com.hknusc.web.dto.MenuEditDTO
import com.hknusc.web.dto.MenuSaveDTO
import com.hknusc.web.util.jwt.JwtTokenProvider
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
    fun saveMenu(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, menuSaveDTO: MenuSaveDTO) =
        menuService.saveMenu(accessToken, menuSaveDTO)

    @PatchMapping
    fun editMenu(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, menuEditDTO: MenuEditDTO) =
        menuService.editMenu(accessToken, menuEditDTO)

    @DeleteMapping("{menuId}")
    fun deleteMenu(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("menuId") menuId: Int
    ) = menuService.deleteMenu(accessToken, menuId)
}