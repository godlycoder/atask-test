package com.banidevv.data.services.ocr

import android.content.Context
import android.content.res.AssetManager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object Assets {
    fun getTessDataPath(context: Context): String {
        // We need to return folder that contains the "tessdata" folder,
        // which is in this sample directly the app's files dir
        return context.filesDir.absolutePath
    }

    val language: String
        get() = "eng"

    fun extractTrainedData(context: Context) {
        val am = context.assets

        val tessDir = File(getTessDataPath(context), "tessdata")
        if (!tessDir.exists()) {
            tessDir.mkdir()
        }
        val engFile = File(tessDir, "eng.traineddata")
        if (!engFile.exists()) {
            copyTrainedData(am, engFile)
        }
    }

    private fun copyTrainedData(
        am: AssetManager,
        outFile: File
    ) {
        try {
            am.open("eng.traineddata").use { `in` ->
                FileOutputStream(outFile).use { out ->
                    val buffer = ByteArray(1024)
                    var read: Int
                    while (`in`.read(buffer).also { read = it } != -1) {
                        out.write(buffer, 0, read)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}