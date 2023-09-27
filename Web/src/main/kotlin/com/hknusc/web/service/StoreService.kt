package com.hknusc.web.service

import com.hknusc.web.dto.store.*
import com.hknusc.web.repository.OperatingDayRepository
import com.hknusc.web.repository.StoreRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import com.hknusc.web.util.type.OperatingDay
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val tokenProvider: JWTTokenProvider,
    private val photoUtility: PhotoUtility,
    private val storeRepository: StoreRepository,
    private val operatingDayRepository: OperatingDayRepository
) {
    fun getStores(userId: Int): List<StoreDTO> {
        val storesDB = storeRepository.getStores(userId)

        val storeList: MutableList<StoreDTO> = mutableListOf()
        storesDB.forEach {
            val operatingDays = operatingDayRepository.getDays(it.id)
            val store = it.convertToStore(photoUtility, operatingDays)
            storeList.add(store)
        }

        return storeList
    }

    fun getStore(userId: Int, userStoreId: Int): StoreDTO {
        val storeDB = storeRepository.getStore(userId, userStoreId)
        val operatingDays = operatingDayRepository.getDays(storeDB.id)
        return storeDB.convertToStore(photoUtility, operatingDays)
    }

    fun saveStore(userId: Int, storeSaveDTO: StoreSaveDTO) {
        val storeDBSaveDTO = storeSaveDTO.convertToStoreDB(photoUtility, userId)

        storeRepository.saveStore(storeDBSaveDTO)

        saveDays(storeDBSaveDTO.id, storeSaveDTO.operatingDays)
    }

    fun editStore(userId: Int, userStoreId: Int, storeEditDTO: StoreEditDTO) {
        val storeDB = storeRepository.getStore(userId, userStoreId)
        storeDB.profilePhoto?.let { photoUtility.deleteImage(it) }
        photoUtility.deleteImages(storeDB.photos)

        val storeDBEditDTO = storeEditDTO.convertToStoreDB(photoUtility, userStoreId)

        storeRepository.editStore(storeDBEditDTO)

        operatingDayRepository.clearDays(userStoreId)
        saveDays(userStoreId, storeEditDTO.operatingDays)
    }

    fun setOpen(userId: Int, userEmail: String, storeOpenDTO: StoreOpenDTO): ResponseEntity<Any> {
        if (storeRepository.setOpen(userId, storeOpenDTO) == 0)
            throw CustomException(ErrorCode.STORE_NOT_FOUND)

        val responseEntity = ResponseEntity.ok()
        if (storeOpenDTO.isOpen) {
            val httpHeaders: HttpHeaders = tokenProvider.generateTokenHeader(userId, userEmail, storeOpenDTO.id)
            responseEntity.headers(httpHeaders)
        }
        return responseEntity.build()
    }

    private fun saveDays(userStoreId: Int, operatingDays: List<OperatingDay>?) {
        if (operatingDays != null) {
            val operatingMutableDays = operatingDays.toMutableList()
            operatingMutableDays.removeIf { it.operatingTimes == null }
            if (operatingDays.isNotEmpty()) {
                val operatingDayDTO = OperatingDayDTO(userStoreId, operatingDays)
                operatingDayRepository.saveDays(operatingDayDTO)
            }
        }
    }
}
