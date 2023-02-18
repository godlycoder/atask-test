package com.banidevv.data.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: ImageEntity)

    @Query("SELECT * FROM image")
    suspend fun fetchAll(): List<ImageEntity>

}