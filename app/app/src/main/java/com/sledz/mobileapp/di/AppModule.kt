package com.sledz.mobileapp.di

import com.sledz.mobileapp.data.remote.MainApi
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        mainApi: MainApi
    ) = MainRepository(mainApi)

    @Singleton
    @Provides
    fun provideApiInterface(): MainApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MainApi::class.java)
    }
}