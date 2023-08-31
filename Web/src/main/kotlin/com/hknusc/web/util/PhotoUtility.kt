package com.hknusc.web.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.util.*

@Component
class PhotoUtility(
    @param:Value("\${photo.uploadPath}") private val uploadPath: String,
    private val objectMapper: ObjectMapper
) {
    fun saveImage(photo: MultipartFile?): String? {
        val originalFilename = photo?.originalFilename
        if (!isImage(photo, originalFilename)) {
            return null
        }
        val uuid = UUID.randomUUID().toString()
        val saveFileName = uuid + "_" + originalFilename
        photo!!.transferTo(File(uploadPath + saveFileName))
        return saveFileName
    }

    fun saveImages(photos: List<MultipartFile>?): List<String> {
        val photoPaths: MutableList<String> = mutableListOf()
        photos?.forEach {
            val path: String? = saveImage(it)
            if (!path.isNullOrEmpty()) {
                photoPaths.add(path)
            }
        }
        return photoPaths
    }

    fun saveImagesAsString(photos: List<MultipartFile>?): String = objectMapper.writeValueAsString(saveImages(photos))

    fun getImagesByString(photos: String): List<String> =
        objectMapper.readValue(
            photos,
            TypeFactory.defaultInstance().constructCollectionType(List::class.java, String::class.java)
        )

    fun deleteImage(path: String) {
        val file = File(uploadPath + path)
        file.delete()
    }

    fun deleteImages(photos: String) {
        val paths = getImagesByString(photos)
        paths.forEach {
            deleteImage(it)
        }
    }

    private fun isImage(photo: MultipartFile?, originalFilename: String?): Boolean {
        if (photo == null || photo.isEmpty || originalFilename == null) {
            return false
        }

        val checkFile = File(originalFilename)
        val type = Files.probeContentType(checkFile.toPath())

        if (type.startsWith("image")) {
            return true
        } else {
            throw CustomException(ErrorCode.NOT_PHOTO_REQUEST)
        }
    }
}
