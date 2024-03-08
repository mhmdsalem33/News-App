package com.salem.apps.data.repo

import com.salem.apps.data.data_source.remote.NewsServices
import com.salem.apps.data.entity.NewsDTO
import com.salem.apps.domain.repo.MainRepository
import retrofit2.Response

class MainRepositoryImpl( private val newsServices: NewsServices) : MainRepository {
    override suspend fun getBitcoinNews( query: String , apiKey: String ): Response<NewsDTO> = newsServices.getBitcoinNews( query , apiKey )
}