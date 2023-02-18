package com.banidevv.data.di

import android.content.Context
import com.banidevv.data.services.local.AppDatabase
import com.banidevv.data.services.local.ImageDao
import com.banidevv.data.services.ocr.Assets
import com.banidevv.data.services.ocr.OcrServices
import com.googlecode.tesseract.android.TessBaseAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    @Singleton
    fun provideTessApi(@ApplicationContext context: Context) : TessBaseAPI {
        Assets.extractTrainedData(context)
        return TessBaseAPI()
    }

    @Provides
    @Singleton
    fun provideTessDataPath(@ApplicationContext context: Context) : String {
        return Assets.getTessDataPath(context)
    }

    @Provides
    @Singleton
    fun provideOcrServices(tessBaseAPI: TessBaseAPI, tessDataPath: String) : OcrServices {
        return OcrServices(tessBaseAPI, tessDataPath)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideImageDao(appDatabase: AppDatabase) : ImageDao {
        return appDatabase.imageDao()
    }

}