package com.banidevv.domain.usecase

import com.banidevv.domain.repository.ImageResultRepository

class GetListImageResult(
    private val repository: ImageResultRepository
) {
    suspend fun execute() = repository.getListResult()

}
