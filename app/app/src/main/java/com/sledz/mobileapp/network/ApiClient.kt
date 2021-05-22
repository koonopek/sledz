package com.sledz.mobileapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASEURL = "http://localhost:8080/"

class ApiClient {
    companion object {
        var retrofit:Retrofit?=null
        fun getApiClient(): Retrofit? {
            val okHttpClient = OkHttpClient()

            if(retrofit==null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}