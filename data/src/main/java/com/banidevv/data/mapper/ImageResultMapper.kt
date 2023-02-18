package com.banidevv.data.mapper

import com.banidevv.data.dto.ImageResultDTO
import com.banidevv.data.services.local.ImageEntity
import com.banidevv.domain.model.ImageResultUiModel

fun ImageResultDTO.toUiModel() : ImageResultUiModel {
    return ImageResultUiModel(
        result,
        image,
        ""
    )
}

fun ImageEntity.toDto() : ImageResultDTO {
    return ImageResultDTO(
        image, text, result.toString()
    )
}