package com.salem.apps.domain.di


import com.salem.apps.data.data_source.remote.NewsServices
import com.salem.apps.data.repo.MainRepositoryImpl
import com.salem.apps.domain.repo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository( newsServices: NewsServices ) : MainRepository = MainRepositoryImpl( newsServices )


}