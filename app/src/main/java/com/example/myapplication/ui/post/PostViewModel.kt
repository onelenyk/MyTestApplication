package com.example.myapplication.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.locale.provider.DBProvider
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.provider.RepositoryProvider
import com.example.myapplication.data.network.repository.FeedCacheRepository
import com.example.myapplication.data.network.utils.ResponseData

class PostViewModel : ViewModel() {
    private val feedRepository by lazy {
        RepositoryProvider.feedRepository
    }

    val comments = MutableLiveData<List<CommentItem>>()

    val user = MutableLiveData<User>()

    val post = MutableLiveData<Post>()

    fun loadData(postId: Int) {
        feedRepository.comments(postId).observeForever {
            when (it) {
                is ResponseData.Success -> {
                    comments.value = it.requireData().map { CommentItem((it)) }
                }
            }
        }

        feedRepository.user(postId).observeForever {
            when (it) {
                is ResponseData.Success -> {
                    user.value = it.requireData()
                }
            }
        }

        feedRepository.post(postId).observeForever {
            when (it) {
                is ResponseData.Success -> {
                    post.value = it.requireData()
                }
            }
        }
    }
}