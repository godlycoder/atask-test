package com.banidevv.data.datasource

import android.graphics.Bitmap
import com.banidevv.data.dto.ImageResultDTO
import com.banidevv.data.mapper.toDto
import com.banidevv.data.services.local.ImageDao
import com.banidevv.data.services.local.ImageEntity
import com.banidevv.data.services.ocr.OcrServices
import javax.inject.Inject
import com.banidevv.domain.Result

class ImageResultLocalDataSource @Inject constructor(
    private val ocrServices: OcrServices,
    private val imageDao: ImageDao
) {
    suspend fun getResult(bitmap: Bitmap): Result<ImageResultDTO> {
        val text = ocrServices.process(bitmap)

        val isAlphabetic = text?.matches(Regex("[a-zA-Z]+"))

        if (isAlphabetic == true) {
            return Result.Failed(Exception("Cannot found arithmetic"))
        }

        if (text == null) {
            return Result.Failed(Exception("Result cannot found"))
        }

        return try {
            val result = text.toInt()
            val entity = ImageEntity(
                0,
                bitmap,
                text,
                result
            )

            imageDao.insert(entity)

            Result.Success(entity.toDto())
        } catch (e: Exception) {
            Result.Failed(e)
        }
    }

    suspend fun getListResult(): Result<List<ImageResultDTO>> {
        return try {
            val result = imageDao.fetchAll()
            Result.Success(result.map { it.toDto() })
        } catch (e: Exception) {
            Result.Failed(e)
        }

    }
}
