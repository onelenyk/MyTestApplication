package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.data.locale.Database
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.toEntities
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var db: Database
    private lateinit var postsDao: PostsDao

    @Before
    @Throws(Exception::class)
    fun createDb() {
        ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            Database::class.java
        )
            .build()

        postsDao = db.postsDao()
    }

    @Test
    fun testInsertPosts(){
        val post = Post(0, 1, "title", "nobody")

        val posts:Posts = arrayListOf(post)
        postsDao.insert(posts.toEntities())

        val postEntity = postsDao.allSync().map {  it.post}

        Assert.assertEquals(posts, postEntity)
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }
}