package com.example.myapplication.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.model.CommentsEntity
import com.example.myapplication.data.model.PostEntity
import com.example.myapplication.data.model.UserEntity


@Database(
    entities = [
        UserEntity::class,
        CommentsEntity::class,
        PostEntity::class
    ], version = 1, exportSchema = false
)


abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun commentsDao(): CommentsDao
    abstract fun postsDao(): PostsDao
}