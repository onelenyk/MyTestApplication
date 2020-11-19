package com.example.myapplication.data.network.provider

import com.example.myapplication.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    val gson: Gson by lazy {
        GsonBuilder().
        setDateFormat("yyyy-MM-dd'T'HH:mm:ss").
        create()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(OkHttpClientProvider.okHttpClient)
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson))
            .build()
    }
}