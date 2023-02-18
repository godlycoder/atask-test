package com.banidevv.mpassigmenttest

sealed class ViewState<out T : Any> {
    object Loading : ViewState<Nothing>()
    data class Success<out T : Any>(val data: T) : ViewState<T>()
    data class Error<out T : Any>(val message: String) : ViewState<T>()
}