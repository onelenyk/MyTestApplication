package com.example.myapplication.feedApi

import androidx.lifecycle.liveData
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.model.Posts
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

class FeedApiPostsTest {

    lateinit var feedApi: FeedService

    lateinit var testPosts:Posts

    @Before
    fun prepareForTest() {
        runBlocking {
            feedApi = mock()

            val post = Post(0, 1, "title", "nobody")

            testPosts = arrayListOf(post)

            val response = Response.success(testPosts)

            given(feedApi.posts()).willReturn(response)
        }
    }


    @Test
    fun testPostSuccess() = runBlocking {
        val resultExpect = true

        val response = feedApi.posts()

        val resultGot = response.isSuccessful

        Assert.assertEquals(resultExpect, resultGot)
    }

    @Test
    fun testPostData() = runBlocking {

        val response = feedApi.posts()

        val dataExpect = testPosts

        val data = response.body()

        Assert.assertEquals(dataExpect, data)
    }

}