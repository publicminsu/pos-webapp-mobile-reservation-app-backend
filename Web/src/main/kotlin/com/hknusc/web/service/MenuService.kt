package com.hknusc.web.service

import com.hknusc.web.dto.MenuDTO
import com.hknusc.web.dto.MenuEditDTO
import com.hknusc.web.exception.CustomException
import com.hknusc.web.exception.ErrorCode
import com.hknusc.web.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(private val menuRepository: MenuRepository) {
    fun getMenus() = menuRepository.getMenus()
    fun getMenu(menuId: Int): MenuDTO {
        try {
            return menuRepository.getMenu(menuId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

    fun saveMenu(menuDTO: MenuDTO) = menuRepository.saveMenu(menuDTO)
    fun editMenu(menuEditDTO: MenuEditDTO) = menuRepository.editMenu(menuEditDTO)
    fun deleteMenu(menuId: Int) = menuRepository.deleteMenu(menuId)
}