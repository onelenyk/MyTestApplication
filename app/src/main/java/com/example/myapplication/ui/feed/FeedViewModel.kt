package com.example.myapplication.ui.feed

import androidx.lifecycle.*
import com.example.myapplication.data.locale.provider.DBProvider
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.provider.RepositoryProvider
import com.example.myapplication.data.network.repository.FeedCacheRepository
import com.example.myapplication.data.network.utils.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val feedRepository by lazy {
        RepositoryProvider.feedRepository
    }

    val feedList = MutableLiveData<List<PostItem>>()

    init {
        loadPosts()
    }

    private fun loadPosts(){
        feedRepository.posts().observeForever {
            when(it){
                is ResponseData.Success -> successPostsLoad(it.requireData())
                is ResponseData.Loading -> {}
                is ResponseData.Error -> {}
            }
        }
    }

    private fun successPostsLoad(posts: Posts){
        val items = posts.map { item -> PostItem(item) }
        feedList.value = items
    }

}