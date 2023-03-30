package com.example.pokemonapp.utils.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManagerFactory {

    fun makeRetrofitService(baseUrl: String): ApiService {
        val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            .connectTimeout(60L  , TimeUnit.SECONDS)
            .readTimeout(60L  , TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS).build()

        val interceptorLogger = HttpLoggingInterceptor()
        interceptorLogger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        httpClient.addInterceptor(interceptorLogger)

        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .client(httpClient.build())
            .build()
            .create(ApiService::class.java)
    }
}