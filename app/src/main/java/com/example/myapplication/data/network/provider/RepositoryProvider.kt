package com.example.myapplication.data.network.provider

import com.example.myapplication.data.network.repository.FeedCacheRepository
import com.example.myapplication.data.network.repository.FeedRepository

object RepositoryProvider{
    val feedRepository: FeedRepository by lazy {
        FeedCacheRepository()
    }

}