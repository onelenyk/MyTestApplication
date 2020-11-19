package com.example.myapplication.ui.feed

import androidx.lifecycle.*
import com.example.myapplication.data.locale.provider.DBProvider
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.provider.RepositoryProvider
import com.example.myapplication.data.network.utils.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {


    private val postsDao by lazy {
        DBProvider.getPostsDao()
    }

    private val feedRepository by lazy {
        RepositoryProvider.feedRepository
    }

    init {
        loadPosts()

        postsDao.all().observeForever{
            feedList.value = it.map { item -> PostItem(item.post) }
        }
    }

    val feedList = MutableLiveData<List<PostItem>>()


    private fun loadPosts(){
        collectData()
    }


    private fun collectData(){
        feedRepository.posts().observeForever {
            when(it){
                is ResponseData.Success -> successPostsLoad(it.requireData())
                is ResponseData.Loading -> {}
                is ResponseData.Error -> {}
            }
        }
    }

    private fun successPostsLoad(posts: Posts){
        savePosts(posts)
    }


    private fun savePosts(posts: Posts){
        postsDao.insert(posts.toEntities())
    }

}