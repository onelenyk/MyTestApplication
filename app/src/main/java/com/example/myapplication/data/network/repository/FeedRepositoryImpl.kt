package com.example.myapplication.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myapplication.data.model.Comments
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.User
import com.example.myapplication.data.network.provider.ApiProvider
import com.example.myapplication.data.network.utils.ResponseData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


class FeedRepositoryImpl : BaseRepository(),FeedRepository {
    private val feedApi = ApiProvider.feedApi

    override fun posts(): LiveData<ResponseData<Posts>> = call {
        feedApi.posts()
    }

    override fun comments(postId: Int): LiveData<ResponseData<Comments>> = call {
        feedApi.comments(postId)
    }

    override fun user(id: Int): LiveData<ResponseData<User>> = call {
        feedApi.user(id)
    }

}