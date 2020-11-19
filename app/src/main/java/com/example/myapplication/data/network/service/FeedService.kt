package com.example.myapplication.data.network.service

import com.example.myapplication.data.model.Comments
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FeedService {
    @GET("posts/")
    suspend fun posts(): Response<Posts>

    @GET("comments/")
    suspend fun comments(@Query("postId") postId :Int): Response<Comments>

    @GET("users/{id}")
    suspend fun user(@Path("id") id :Int): Response<User>
}
