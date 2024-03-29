package com.hknusc.web.repository

import com.hknusc.web.dto.menu.MenuDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuRepository {
    fun getMenus(storeId: Int): List<MenuDTO>
    fun getMenu(menuId: Int, storeId: Int): MenuDTO?
}
