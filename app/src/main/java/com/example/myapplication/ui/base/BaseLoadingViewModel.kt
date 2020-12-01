package com.example.myapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

open class BaseLoadingViewModel : ViewModel(),
    Observer<BaseLoadingFragment.BaseState> {
    val loading  =
        MutableLiveData<BaseLoadingFragment.BaseState>()

    val toaster = MutableLiveData<String>()

    fun toast(string:String){
        toaster.value = string
    }

    override fun onChanged(t: BaseLoadingFragment.BaseState?) {
        loading.value = t
    }

}