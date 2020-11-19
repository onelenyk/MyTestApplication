package com.example.myapplication.data.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
){
    fun toEntity():PostEntity {
        return PostEntity(post = this)
    }
}
