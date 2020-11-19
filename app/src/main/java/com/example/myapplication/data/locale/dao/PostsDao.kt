package com.example.myapplication.data.locale.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.PostEntity
import com.example.myapplication.data.model.UserEntity

@Dao
abstract class PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(items: List<PostEntity>)

    @Query("DELETE FROM posts")
    abstract fun clear()

    @Query("SELECT * FROM posts")
    abstract fun all(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE postid = :id")
    abstract fun postSync(id:Int): PostEntity
}