package com.example.myapplication.data.network.provider

import com.example.myapplication.data.network.service.FeedService

object ApiProvider {
    val feedApi: FeedService by lazy {
        RetrofitProvider.retrofit.create(FeedService::class.java)
    }
}