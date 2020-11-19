package com.example.myapplication.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myapplication.data.network.utils.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.NullPointerException

open class BaseRepository {
    fun <A> call( it: (suspend () -> Response<A>)): LiveData<ResponseData<A>> {
        return liveData(Dispatchers.IO) {
            emit(ResponseData.Loading())
            try {
                val result = it.invoke()
                emit(result.specifyResponseData())
            } catch (e: Exception) {
                val data = e.findOut()
                emit(ResponseData.Error<A>(data))
            }
        }
    }

    private fun <T> Response<T>.specifyResponseData():ResponseData<T>{
        return if (this.isSuccessful){
            parseSuccessBody(this.body())
        } else {
            parseErrorBody(this.errorBody())
        }
    }


    private fun <T> parseSuccessBody(body: T?):ResponseData<T>{
        return when(body){
            null -> {
                ResponseData.Error(nullBodyError())
            }
            is List<*> -> {
                if(body.isEmpty()){
                    ResponseData.Error<T>(emptyListError())
                }else{
                    ResponseData.Success<T>(body)
                }
            }
            else -> {
                ResponseData.Success(body)
            }
        }
    }

    private fun <T> parseErrorBody(body: ResponseBody?):ResponseData<T>{
        try {
            val bodyString = body?.string() ?: throw NullPointerException()
            return ResponseData.Error(bodyString)
        }catch (e:Exception){
            val data = e.findOut()
            return ResponseData.Error(data)
        }
    }

    private fun emptyListError():String{
        return "never"
    }

    private fun nullBodyError():String{
        return "say never"
    }

    companion object{
        fun Exception.findOut():String{
            val error = this.cause.toString()
            val message = this.message

            printStackTrace()
            return message ?: error
        }

    }
}