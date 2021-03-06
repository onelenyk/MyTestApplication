package com.example.myapplication.data.network.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.model.Comments
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.User
import com.example.myapplication.data.network.utils.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FeedRepository{
    fun post(postId :Int): LiveData<ResponseData<Post>>
    fun posts(): LiveData<ResponseData<Posts>>

    fun comments(postId :Int): LiveData<ResponseData<Comments>>

    fun user(postId :Int): LiveData<ResponseData<User>>
}
