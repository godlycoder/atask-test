package com.banidevv.mpassigmenttest

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banidevv.domain.model.ImageResultUiModel
import com.banidevv.domain.onFailure
import com.banidevv.domain.onSuccess
import com.banidevv.domain.usecase.GetImageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImageResult: GetImageResult
) : ViewModel() {

    private val _uiState = MutableLiveData<ViewState<ImageResultUiModel>>()
    val uiState : LiveData<ViewState<ImageResultUiModel>>
        get() = _uiState


    fun processPhoto(bitmap: Bitmap) = viewModelScope.launch(Dispatchers.Main) {
        _uiState.value = ViewState.Loading
        getImageResult.execute(bitmap).collect { result ->
            result.onSuccess {
                _uiState.value = ViewState.Success(it)
            }.onFailure {
                _uiState.value = ViewState.Error(it.message.toString())
            }
        }

    }
}