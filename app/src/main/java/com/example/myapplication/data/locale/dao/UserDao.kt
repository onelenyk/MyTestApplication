package com.example.myapplication.data.locale.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.UserEntity

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(items: List<UserEntity>)

    @Query("DELETE FROM users")
    abstract fun clear()

    @Query("SELECT * FROM users WHERE user_id = :id")
    abstract fun user(id:Int): LiveData<UserEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE user_id = :userId)")
    abstract fun exists(userId: Int): Boolean
}