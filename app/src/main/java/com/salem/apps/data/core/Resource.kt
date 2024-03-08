package com.salem.apps.data.core

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
    class Idle<T> : Resource<T>()
    class Cached<T>(data: T) : Resource<T>(data)
}

