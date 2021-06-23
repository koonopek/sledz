package com.sledz.mobileapp.di

import com.sledz.mobileapp.BuildConfig
import com.sledz.mobileapp.data.Store
import com.sledz.mobileapp.data.remote.MainApi
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }
                    .addInterceptor({
                        val request = it.request()
                        val requestWithAuth = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + Store(activity).read(Api))
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainApi::class.java)
    }
}