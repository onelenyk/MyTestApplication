package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.data.locale.Database
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CommentsDaoTest {

    private lateinit var db: Database
    private lateinit var commentsDao: CommentsDao

    @Before
    @Throws(Exception::class)
    fun createDb() {
        ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            Database::class.java
        )
            .build()

        commentsDao = db.commentsDao()
    }

    @Test
    fun testInsertComments(){
        val post = Post(0, 0, "Title", "Body")
        val comment = Comment(post.id, 1, "test","email", "test")
        val comments:Comments = arrayListOf(comment)

        commentsDao.insert(comments.toEntities(post.id))

        val result = commentsDao.commentsSync(postId = post.id).map { it.toComment() }

        Assert.assertEquals(comments, result)
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }
}