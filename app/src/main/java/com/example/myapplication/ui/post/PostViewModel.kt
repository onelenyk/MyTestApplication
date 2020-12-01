package com.example.myapplication.ui.post

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.repository.FeedRepository
import com.example.myapplication.data.network.utils.ResponseData
import com.example.myapplication.ui.base.BaseLoadingViewModel
import com.example.myapplication.data.network.utils.observeForever

class PostViewModel @ViewModelInject constructor(
    private val feedRepository: FeedRepository
)  : BaseLoadingViewModel() {

    val comments = MutableLiveData<List<CommentItem>>()

    val user = MutableLiveData<User>()

    val post = MutableLiveData<Post>()

    fun loadData(postId: Int) {
        feedRepository.comments(postId).observeForever(this, Observer {
            when (it) {
                is ResponseData.Success -> {
                    comments.value = it.requireData().map { CommentItem((it)) }
                }
            }
        })

        feedRepository.user(postId).observeForever(this, Observer {
            when (it) {
                is ResponseData.Success -> {
                    user.value = it.requireData()
                }
            }
        })

        feedRepository.post(postId).observeForever(this, Observer {
            when (it) {
                is ResponseData.Success -> {
                    post.value = it.requireData()
                }
            }
        })
    }
}