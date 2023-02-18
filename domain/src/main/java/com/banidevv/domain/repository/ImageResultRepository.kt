package com.banidevv.domain.repository

import android.graphics.Bitmap
import com.banidevv.domain.Result
import com.banidevv.domain.model.ImageResultUiModel
import kotlinx.coroutines.flow.Flow

interface ImageResultRepository {
    suspend fun getResult(bitmap: Bitmap): Flow<Result<ImageResultUiModel>>
    suspend fun getListResult(): Flow<Result<List<ImageResultUiModel>>>
}
