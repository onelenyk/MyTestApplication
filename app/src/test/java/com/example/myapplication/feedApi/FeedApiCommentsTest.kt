package com.example.myapplication.feedApi

import androidx.lifecycle.liveData
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.Comment
import com.example.myapplication.data.model.Comments
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

class FeedApiCommentsTest {

    lateinit var feedApi: FeedService

    lateinit var testComments:Comments

    @Before
    fun prepareForTest() {
        runBlocking {
            feedApi = mock()

            val comment = Comment(0,1,"title","email","nobody")


            testComments = arrayListOf(comment)

            val response = Response.success(testComments)

            given(feedApi.comments(any())).willReturn(response)
        }
    }



    @Test
    fun testCommentsSuccess() = runBlocking {
        val resultExpect = true

        val response = feedApi.comments(any())

        val resultGot = response.isSuccessful

        Assert.assertEquals(resultExpect, resultGot)
    }

    @Test
    fun testCommentsData() = runBlocking {
        val response = feedApi.comments(any())

        val dataExpect = testComments

        val data = response.body()

        Assert.assertEquals(dataExpect, data)
    }

}