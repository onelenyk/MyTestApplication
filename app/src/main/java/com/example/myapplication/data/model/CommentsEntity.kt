package com.example.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentsEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                          val postId:Int,
                          @Embedded(prefix = "comment_") val comment: Comment
){
    fun toComment():Comment{
        return comment
    }
}