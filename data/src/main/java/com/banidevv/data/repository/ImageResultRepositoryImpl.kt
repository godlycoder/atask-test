package com.banidevv.data.repository

import android.graphics.Bitmap
import com.banidevv.data.datasource.ImageResultLocalDataSource
import com.banidevv.data.mapper.toUiModel
import com.banidevv.domain.model.ImageResultUiModel
import com.banidevv.domain.repository.ImageResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.banidevv.domain.Result
import com.banidevv.domain.onFailure
import com.banidevv.domain.onSuccess

class ImageResultRepositoryImpl @Inject constructor(
    private val localDataSource: ImageResultLocalDataSource
) : ImageResultRepository {

    override suspend fun getResult(bitmap: Bitmap): Flow<Result<ImageResultUiModel>> {
        val result = localDataSource.getResult(bitmap)

        return flow<Result<ImageResultUiModel>> {
            result.onSuccess {
                emit(Result.Success(it.toUiModel()))
            }.onFailure {
                emit(Result.Failed(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getListResult(): Flow<Result<List<ImageResultUiModel>>> {
        val result = localDataSource.getListResult()

        return flow<Result<List<ImageResultUiModel>>> {
            result.onSuccess { list ->
                emit(Result.Success(list.map { it.toUiModel() }))
            }.onFailure {
                emit(Result.Failed(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}