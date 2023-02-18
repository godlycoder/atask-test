package com.banidevv.data.di

import com.banidevv.data.repository.ImageResultRepositoryImpl
import com.banidevv.domain.repository.ImageResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsImageResultRepository(
        repositoryImpl: ImageResultRepositoryImpl
    ) : ImageResultRepository
}