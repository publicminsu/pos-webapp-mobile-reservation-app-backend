package com.hknusc.web.repository

import com.hknusc.web.dto.MenuDTO
import com.hknusc.web.dto.MenuEditDTO
import com.hknusc.web.dto.MenuSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuRepository {
    fun getMenus(storeId: Int): List<MenuDTO>
    fun getMenu(menuId: Int, storeId: Int): MenuDTO?
    fun saveMenu(storeId: Int, menuSaveDTO: MenuSaveDTO): Int
    fun editMenu(storeId: Int, menuEditDTO: MenuEditDTO): Int
    fun deleteMenu(menuId: Int, storeId: Int): Int
}