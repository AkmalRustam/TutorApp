package com.akmaldev.tutorapp.util.helper

sealed class WrappedResponse<out T> {
    data class Success<out T>(val data: T) : WrappedResponse<T>()
    data class Error(val message: String, val code: Int? = null) : WrappedResponse<Nothing>()
    data object Loading : WrappedResponse<Nothing>()
}