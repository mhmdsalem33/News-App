package com.salem.apps.domain.usecases

import android.util.Log
import com.salem.apps.data.core.ResponseState
import com.salem.apps.data.entity.toDomain
import com.salem.apps.data.network_extentions.safeApiCall
import com.salem.apps.domain.models.NewsModel
import com.salem.apps.domain.repo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBitCoinNewsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke( query : String , apiKey : String)  : Flow<ResponseState<NewsModel>> = flow {
        try {
            emit(ResponseState.Loading())
            val response = safeApiCall(
                apiCall = { mainRepository.getBitcoinNews(query, apiKey)} ,
                mapper =  { newsDto -> newsDto.toDomain() }
            )
            emit(response)
        }catch ( t : Throwable){
            Log.e("testApp" , t.message ?: "")
        }
    }
}