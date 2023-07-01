package com.hknusc.web.controller

import com.hknusc.web.service.MenuService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menus")
class MenuController(private val menuService: MenuService) {
}