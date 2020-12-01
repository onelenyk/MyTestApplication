package com.example.myapplication.ui.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.repository.FeedRepository
import com.example.myapplication.data.network.utils.ResponseData
import com.example.myapplication.ui.base.BaseLoadingViewModel
import com.example.myapplication.data.network.utils.observeForever


class FeedViewModel @ViewModelInject constructor(
    private val feedRepository: FeedRepository
) : BaseLoadingViewModel() {

    val feedList = MutableLiveData<List<PostItem>>()


    init {
        loadPosts()
    }

    private fun loadPosts(){
        feedRepository.posts().observeForever(this, Observer {
            when(it){
                is ResponseData.Success -> successPostsLoad(it.requireData())
                is ResponseData.Error -> {
                    toast(it.error)
                }
            }
        })
    }

    private fun successPostsLoad(posts: Posts){
        val items = posts.map { item -> PostItem(item) }
        feedList.value = items
    }

}