package com.example.myapplication.data.network.utils

sealed class ResponseData<T> {
    class Success<T>(val data: T?) : ResponseData<T>(){
        fun requireData():T{
            return data ?: throw NullPointerException("Data never should be null there")
        }
    }
    class Loading<T> : ResponseData<T>()
    class Error<T>(val error: String) : ResponseData<T>()
}

