package com.banidevv.mpassigmenttest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banidevv.domain.model.ImageResultUiModel
import com.banidevv.domain.onFailure
import com.banidevv.domain.onSuccess
import com.banidevv.domain.usecase.GetListImageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getListImageResult: GetListImageResult
) : ViewModel() {

    private val _uiState = MutableLiveData<ViewState<List<ImageResultUiModel>>>()
    val uiState : LiveData<ViewState<List<ImageResultUiModel>>>
        get() = _uiState


    fun getListImage() = viewModelScope.launch(Dispatchers.Main) {
        _uiState.value = ViewState.Loading
        getListImageResult.execute().collect { result ->
            result.onSuccess {
                _uiState.value = ViewState.Success(it)
            }.onFailure {
                _uiState.value = ViewState.Error(it.message.toString())
            }
        }
    }

}