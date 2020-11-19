package com.example.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      @Embedded(prefix = "user_") val user:User
)