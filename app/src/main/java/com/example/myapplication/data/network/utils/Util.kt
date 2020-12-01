package com.example.myapplication.data.network.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myapplication.ui.base.BaseLoadingFragment

@MainThread
fun <T> LiveData<ResponseData<T>>.observeForever(
    handler: Observer<BaseLoadingFragment.BaseState>,
    observer : Observer<ResponseData<T>>
){
    val obs = ResponseObserver(
        handler,
        observer
    )
    observeForever(obs)
}

class ResponseObserver<T>(private val handler: Observer<BaseLoadingFragment.BaseState>, private val observer : Observer<ResponseData<T>>) :
    Observer<ResponseData<T>> {
    override fun onChanged(event: ResponseData<T>) {
        when(event){
            is ResponseData.Loading -> handler.onChanged(BaseLoadingFragment.BaseState.PROGRESS_TRUE)
            is ResponseData.Success -> handler.onChanged(BaseLoadingFragment.BaseState.PROGRESS_FALSE)
            is ResponseData.Error -> handler.onChanged(BaseLoadingFragment.BaseState.PROGRESS_FALSE)
        }
        observer.onChanged(event)
    }
}