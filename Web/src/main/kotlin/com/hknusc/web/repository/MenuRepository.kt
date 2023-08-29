package com.hknusc.web.repository

import com.hknusc.web.dto.menu.MenuDBSaveDTO
import com.hknusc.web.dto.menu.MenuDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuRepository {
    fun getMenus(storeId: Int): List<MenuDTO>
    fun getMenu(menuId: Int, storeId: Int): MenuDTO?
    fun saveMenu(menuDBSaveDTO: MenuDBSaveDTO)
    fun editMenu(menuDTO: MenuDTO): Int
    fun deleteMenu(menuId: Int, storeId: Int): Int
}
