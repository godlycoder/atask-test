package com.banidevv.data.services.ocr


import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI
import javax.inject.Inject

class OcrServices @Inject constructor(
    private val tessApi : TessBaseAPI,
    private val tessDataPath: String
) {
    fun process(bitmap: Bitmap) : String? {

        if (!tessApi.init(tessDataPath, Assets.language, TessBaseAPI.OEM_LSTM_ONLY)) {
            return  null
        }

        tessApi.setImage(bitmap)

        return tessApi.utF8Text
    }
}