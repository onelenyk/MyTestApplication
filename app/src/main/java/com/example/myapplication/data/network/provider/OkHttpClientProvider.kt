package com.example.myapplication.data.network.provider

import okhttp3.OkHttpClient

object OkHttpClientProvider {
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }
}