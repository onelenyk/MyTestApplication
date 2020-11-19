package com.example.myapplication.data.model

data class User(
    val id:Int,
    val name:String,
    val username:String
){
    fun toEntity():UserEntity{
        return UserEntity(user = this)
    }
}