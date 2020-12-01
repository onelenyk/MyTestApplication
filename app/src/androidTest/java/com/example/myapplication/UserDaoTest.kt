package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.data.locale.Database
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.model.Posts
import com.example.myapplication.data.model.User
import com.example.myapplication.data.model.toEntities
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var db: Database
    private lateinit var userDao: UserDao

    @Before
    @Throws(Exception::class)
    fun createDb() {
        ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            Database::class.java
        )
            .build()

        userDao = db.userDao()
    }

    @Test
    fun testInsertUser(){
        val user = User(99, "Test","Test username")

        userDao.insert(user.toEntity())

        val result = userDao.userSync(user.id).user

        Assert.assertEquals(user, result)
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }
}