package com.example.myapplication.data.network.provider

import com.example.myapplication.data.network.repository.FeedRepository
import com.example.myapplication.data.network.repository.FeedRepositoryImpl

object RepositoryProvider{
    val feedRepository: FeedRepository by lazy {
        FeedRepositoryImpl()
    }

}