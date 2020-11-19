package com.example.myapplication.data.locale.provider

import androidx.room.Room
import com.example.myapplication.application.MainApplication
import com.example.myapplication.data.locale.Database
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao

object DBProvider {
    private var roomInstance: Database? = null

    private fun getDb(): Database {
        if (roomInstance == null) {
            roomInstance = Room.databaseBuilder(
                MainApplication.context, Database::class.java
                , "ILDatabase"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        return roomInstance as Database
    }

    fun getPostsDao(): PostsDao{
        return getDb().postsDao()
    }

    fun getCommentsDao(): CommentsDao{
        return getDb().commentsDao()
    }

    fun getUserDao(): UserDao{
        return getDb().userDao()
    }
}