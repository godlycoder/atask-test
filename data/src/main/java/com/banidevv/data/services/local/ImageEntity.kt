package com.banidevv.data.services.local

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: Bitmap,
    val text: String,
    val result: Int
)
