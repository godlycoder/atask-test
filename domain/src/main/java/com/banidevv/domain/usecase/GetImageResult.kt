package com.banidevv.domain.usecase

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow
import com.banidevv.domain.Result
import com.banidevv.domain.model.ImageResultUiModel
import com.banidevv.domain.repository.ImageResultRepository

class GetImageResult(private val repository: ImageResultRepository) {
    suspend fun execute(bitmap: Bitmap) : Flow<Result<ImageResultUiModel>> {
        return repository.getResult(bitmap)
    }
}