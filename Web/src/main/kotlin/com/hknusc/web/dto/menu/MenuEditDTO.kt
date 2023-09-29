package com.hknusc.web.dto.menu

import com.hknusc.web.util.PhotoUtility
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile
import java.awt.Menu

data class MenuEditDTO(
    val id: Int,
    val name: String,
    @field:PositiveOrZero
    val price: Int,
    val photo: MultipartFile?,
    val category: String?,
    val detail: String?
) {
    fun convertToMenu(storeId: Int, photoUtility: PhotoUtility) = MenuDTO(
        id = id,
        storeId = storeId,
        name = name,
        price = price,
        photo = photoUtility.saveImage(photo),
        category = category,
        detail = detail
    )
}
