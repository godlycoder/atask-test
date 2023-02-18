package com.banidevv.domain.di

import com.banidevv.domain.repository.ImageResultRepository
import com.banidevv.domain.usecase.GetImageResult
import com.banidevv.domain.usecase.GetListImageResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetImageResult(repository: ImageResultRepository)
        = GetImageResult(repository)

    @Provides
    fun providesGetListImageResult(repository: ImageResultRepository)
            = GetListImageResult(repository)
}