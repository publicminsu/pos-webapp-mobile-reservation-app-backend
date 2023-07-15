package com.hknusc.web.service

import com.hknusc.web.dto.MenuDTO
import com.hknusc.web.dto.MenuEditDTO
import com.hknusc.web.dto.MenuSaveDTO
import com.hknusc.web.exception.CustomException
import com.hknusc.web.exception.ErrorCode
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.repository.MenuRepository
import com.hknusc.web.util.PhotoUtility
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val tokenProvider: JwtTokenProvider,
    private val photoUtility: PhotoUtility,
    private val menuRepository: MenuRepository
) {
    fun getMenus(bearerAccessToken: String): List<MenuDTO> {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims).toInt()

        return menuRepository.getMenus(userStoreId)
    }

    fun getMenu(bearerAccessToken: String, menuId: Int): MenuDTO {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims).toInt()

        try {
            return menuRepository.getMenu(menuId, userStoreId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

    fun saveMenu(bearerAccessToken: String, menuSaveDTO: MenuSaveDTO) {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims).toInt()

        val photo = menuSaveDTO.photo
        val photoPath = photoUtility.saveImage(photo)

        val menuDTO = MenuDTO(
            storeId = userStoreId,
            photo = photoPath,
            name = menuSaveDTO.name,
            price = menuSaveDTO.price,
            category = menuSaveDTO.category
        )

        if (menuRepository.saveMenu(menuDTO) == 0) {
            throw CustomException(ErrorCode.MENU_NOT_SAVED)
        }
    }

    fun editMenu(bearerAccessToken: String, menuEditDTO: MenuEditDTO) {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims).toInt()

        if (menuRepository.editMenu(userStoreId, menuEditDTO) == 0) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

    fun deleteMenu(bearerAccessToken: String, menuId: Int) {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims).toInt()

        if (menuRepository.deleteMenu(menuId, userStoreId) == 0) {
            throw CustomException(ErrorCode.MENU_NOT_FOUND)
        }
    }

}