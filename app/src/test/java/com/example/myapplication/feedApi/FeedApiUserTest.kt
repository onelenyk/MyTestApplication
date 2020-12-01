package com.example.myapplication.feedApi

import androidx.lifecycle.liveData
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.User
import com.example.myapplication.data.model.Users
import com.example.myapplication.data.network.repository.FeedCacheRepository
import com.example.myapplication.data.network.repository.FeedRepository
import com.example.myapplication.data.network.service.FeedService
import com.example.myapplication.data.network.utils.ResponseData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class FeedApiUserTest {

    lateinit var feedApi: FeedService

    lateinit var testUser:User

    @Before
    fun prepareForTest() {
        runBlocking {
            feedApi = mock()

            val user = User(0, "title","username")

            testUser = user
            val response = Response.success(user)

            given(feedApi.user(any())).willReturn(response)
        }
    }



    @Test
    fun testUserSuccess() = runBlocking {
        val resultExpect = true

        val response = feedApi.user(any())

        val resultGot = response.isSuccessful

        Assert.assertEquals(resultExpect, resultGot)
    }

    @Test
    fun testUserData() = runBlocking {
        val response = feedApi.user(any())

        val dataExpect = testUser

        val data = response.body()

        Assert.assertEquals(dataExpect, data)
    }

}