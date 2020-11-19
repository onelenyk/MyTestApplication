package com.example.myapplication.data.locale.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.CommentsEntity
import com.example.myapplication.data.model.UserEntity

@Dao
abstract class CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: CommentsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(items: List<CommentsEntity>)

    @Query("DELETE FROM comments")
    abstract fun clear()

    @Query("SELECT * FROM comments WHERE postId = :postId")
    abstract fun commentsSync(postId:Int): List<CommentsEntity>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    abstract fun comments(postId:Int): LiveData<List<CommentsEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM comments WHERE postId = :postId)")
    abstract fun exists(postId: Int): Boolean
}