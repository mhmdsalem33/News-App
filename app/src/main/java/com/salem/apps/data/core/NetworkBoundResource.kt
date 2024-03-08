package com.salem.apps.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    hasInternetConnection: Boolean,
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline getCachedData: suspend () -> ResultType,
    crossinline shouldFetch: (ResultType, hasInternetConnection: Boolean) -> Boolean = { _, _ -> true }
): Flow<Resource<ResultType>> = flow {
    val cachedData = getCachedData()
    emit(Resource.Loading(cachedData))

    if (shouldFetch(cachedData, hasInternetConnection)) {
        try {
            val fetchedData = fetch()
            saveFetchResult(fetchedData)
        } catch (t: Throwable) {
            emit(Resource.Error( throwable = t , data = cachedData ))
            return@flow
        }
    } else {
        emit(Resource.Cached(cachedData))
    }

    emitAll(query().map { Resource.Success(it) })
}
