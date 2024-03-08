package com.salem.apps.data.data_source.remote

import com.salem.apps.data.entity.NewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsServices {
    @GET("everything")
    suspend fun getBitcoinNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsDTO>
}