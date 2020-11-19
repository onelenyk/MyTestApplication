package com.example.myapplication.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.locale.provider.DBProvider
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.provider.RepositoryProvider
import com.example.myapplication.data.network.utils.ResponseData

class PostViewModel : ViewModel() {
    private val userDao by lazy {
        DBProvider.getUserDao()
    }

    private val postsDao by lazy {
        DBProvider.getPostsDao()
    }
    private val commentsDao by lazy {
        DBProvider.getCommentsDao()
    }

    private val feedRepository by lazy {
        RepositoryProvider.feedRepository
    }

    init {

    }

    val comments = MutableLiveData<List<CommentItem>>()

    val user = MutableLiveData<User>()

    val post = MutableLiveData<Post>()

    val userObserver = Observer<ResponseData<User>>{
        when(it){
            is ResponseData.Success -> {
                saveUsers(it.requireData())
            }
            is ResponseData.Loading -> {}
            is ResponseData.Error -> {}
        }
    }

    val commentObserver = Observer<Pair<Post, ResponseData<Comments>>>{
        when(it.second){
            is ResponseData.Success -> {
                val map = Pair(it.first, (it.second as ResponseData.Success<Comments>).requireData())
                saveComments(map)
            }
            is ResponseData.Loading -> {}
            is ResponseData.Error -> {}
        }
    }

    fun loadUser(post: Post){
        feedRepository.user(post.userId).observeForever(userObserver)
    }

    fun loadComments(post: Post) {
        Transformations.map(feedRepository.comments(post.id)) {
            Pair(post, it)
        }.observeForever(commentObserver)
    }

    fun saveComments(comments:Pair<Post, Comments>){
        val result = comments.second.toEntities(comments.first)
        commentsDao.insert(result)
    }


    fun saveUsers(user: User){
        val r = user.toEntity()
        userDao.insert(r)
    }



    fun loadData(postId:Int){
        val post = postsDao.postSync(postId).post

        commentsDao.comments(postId).observeForever{
            comments.value = it.map { CommentItem((it.toComment())) }
        }

        userDao.user(post.userId).observeForever{
            if(it != null){
                user.value = it.user
            }
        }

        this.post.value = post
        load(post)
    }

    fun load(post: Post){
        if(!commentsDao.exists(post.id)){
            loadComments(post)
        }

        if(!userDao.exists(post.userId)){
            loadUser(post)
        }
    }
}