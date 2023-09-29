package com.hknusc.web.dto.menu

import com.hknusc.web.util.PhotoUtility
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile

data class MenuSaveDTO(
    val name: String,
    @field:PositiveOrZero
    val price: Int,
    val photo: MultipartFile?,
    val category: String?,
    val detail: String?
) {
    fun convertToMenuDB(storeId: Int, photoUtility: PhotoUtility) = MenuDBSaveDTO(
        storeId = storeId,
        name = name,
        price = price,
        photo = photoUtility.saveImage(photo),
        category = category,
        detail = detail
    )
}
