package com.gooner.domain.util

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T): ResponseResult<T>()
    data class Error(val exception: Exception): ResponseResult<Nothing>()
    object Loading: ResponseResult<Nothing>()
}