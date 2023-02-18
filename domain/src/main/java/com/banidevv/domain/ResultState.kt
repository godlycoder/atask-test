package com.banidevv.domain



sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failed<T>(val error: Exception) : Result<T>()
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T> Result<T>.onFailure(action: (Exception) -> Unit) {
    if (this is Result.Failed) action(error)
}

inline fun <T, R> Result.Success<T>.to(action: (T) -> R): Result<R> {
    return Result.Success(action(data))
}
