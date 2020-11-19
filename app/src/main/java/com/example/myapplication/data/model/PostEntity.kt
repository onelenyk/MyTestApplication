package com.example.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      @Embedded(prefix = "post") val post: Post
)