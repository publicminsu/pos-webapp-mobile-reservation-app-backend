package com.hknusc.web.repository

import com.hknusc.web.dto.MenuDTO
import com.hknusc.web.dto.MenuEditDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuRepository {
    fun getMenus(storeId: Int): List<MenuDTO>
    fun getMenu(menuId: Int, storeId: Int): MenuDTO?
    fun saveMenu(menuDTO: MenuDTO)
    fun editMenu(menuEditDTO: MenuEditDTO)
    fun deleteMenu(menuId: Int, storeId: Int): Int
}