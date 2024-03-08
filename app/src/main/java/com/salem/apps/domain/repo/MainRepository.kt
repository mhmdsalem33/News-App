package com.salem.apps.domain.repo

import com.salem.apps.data.entity.NewsDTO
import retrofit2.Response

interface MainRepository {
    suspend fun getBitcoinNews( query: String, apiKey: String): Response<NewsDTO>
}