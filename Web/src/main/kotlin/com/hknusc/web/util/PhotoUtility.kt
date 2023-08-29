package com.hknusc.web.util

import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.util.*

@Component
class PhotoUtility(@param:Value("\${photo.uploadPath}") private val uploadPath: String) {
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

    fun deleteImage(path: String) {
        val file = File(uploadPath + path)
        file.delete()
    }

    private fun isImage(photo: MultipartFile?, originalFilename: String?): Boolean {
        if (photo == null || originalFilename == null) {
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
