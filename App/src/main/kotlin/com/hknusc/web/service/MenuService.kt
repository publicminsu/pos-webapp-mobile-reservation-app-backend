package com.hknusc.web.service

import com.hknusc.web.dto.menu.MenuDTO
import com.hknusc.web.repository.MenuRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun getMenus(storeId: Int): List<MenuDTO> {
        return menuRepository.getMenus(storeId)
    }

    fun getMenu(storeId: Int, menuId: Int): MenuDTO {
        try {
            return menuRepository.getMenu(menuId, storeId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }
}
