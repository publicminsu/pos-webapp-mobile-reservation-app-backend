package com.hknusc.web.service

import com.hknusc.web.dto.menu.MenuDBSaveDTO
import com.hknusc.web.dto.menu.MenuDTO
import com.hknusc.web.dto.menu.MenuEditDTO
import com.hknusc.web.dto.menu.MenuSaveDTO
import com.hknusc.web.repository.MenuRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val photoUtility: PhotoUtility,
    private val menuRepository: MenuRepository
) {
    fun getMenus(userStoreId: Int) = menuRepository.getMenus(userStoreId)

    fun getMenu(userStoreId: Int, menuId: Int) = try {
        menuRepository.getMenu(menuId, userStoreId)!!
    } catch (_: Exception) {
        throw CustomException(ErrorCode.MENU_NOT_FOUND)
    }

    fun saveMenu(userStoreId: Int, menuSaveDTO: MenuSaveDTO) {
        val photo = menuSaveDTO.photo
        val photoPath = photoUtility.saveImage(photo)

        val menuDBSaveDTO = MenuDBSaveDTO(
            storeId = userStoreId,
            name = menuSaveDTO.name,
            price = menuSaveDTO.price,
            photo = photoPath,
            category = menuSaveDTO.category
        )

        try {
            menuRepository.saveMenu(menuDBSaveDTO)
        } catch (_: Exception) {
            throw CustomException(ErrorCode.MENU_NOT_SAVED)
        }
    }

    fun editMenu(userStoreId: Int, menuEditDTO: MenuEditDTO) {
        deleteOldMenuPhoto(menuEditDTO.id, userStoreId)

        val photo = menuEditDTO.photo
        val photoPath = photoUtility.saveImage(photo)

        val menuDTO = MenuDTO(
            id = menuEditDTO.id,
            storeId = userStoreId,
            name = menuEditDTO.name,
            price = menuEditDTO.price,
            photo = photoPath,
            category = menuEditDTO.category
        )

        if (menuRepository.editMenu(menuDTO) == 0) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

    fun deleteMenu(userStoreId: Int, menuId: Int) {
        deleteOldMenuPhoto(menuId, userStoreId)

        if (menuRepository.deleteMenu(menuId, userStoreId) == 0) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

    private fun deleteOldMenuPhoto(menuId: Int, storeId: Int) {
        val oldMenu: MenuDTO = try {
            menuRepository.getMenu(menuId, storeId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }

        if (oldMenu.photo != null) {
            photoUtility.deleteImage(oldMenu.photo)
        }
    }
}
