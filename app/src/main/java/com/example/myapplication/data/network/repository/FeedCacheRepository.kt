package com.example.myapplication.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.myapplication.data.locale.provider.DBProvider
import com.example.myapplication.data.model.*
import com.example.myapplication.data.network.provider.ApiProvider
import com.example.myapplication.data.network.utils.ResponseData
import kotlinx.coroutines.Dispatchers
import java.lang.NullPointerException

class FeedCacheRepository : BaseRepository(),FeedRepository {
    private val feedApi = ApiProvider.feedApi

    private val postsDao by lazy {
        DBProvider.getPostsDao()
    }

    private val userDao by lazy {
        DBProvider.getUserDao()
    }


    private val commentsDao by lazy {
        DBProvider.getCommentsDao()
    }

    override fun post(postId :Int): LiveData<ResponseData<Post>> = liveData {
        if(existPost(postId)){
            emit(localePost(postId))
        }else{
            throw NullPointerException()
        }
    }

    override fun posts(): LiveData<ResponseData<Posts>> = liveData {
        if(existPosts()){
            emit(localePosts())
        }else{
            val source  = Transformations.map(tempPosts()) {
                when (it) {
                    is ResponseData.Success -> {
                        val posts = it.requireData()
                        savePosts(posts)
                        it
                    }
                    else -> it
                }
            }
            emitSource(source)
        }
    }

    override fun user(postId: Int): LiveData<ResponseData<User>> = liveData {
        val userId = getUserIdByPostId(postId)
        if(existUser(userId)){
            emit(localeUser(userId))
        }else{
            val source  = Transformations.map(tempUser(userId)) {
                when (it) {
                    is ResponseData.Success -> {
                        val user = it.requireData()
                        saveUser(user)
                        it
                    }
                    else -> it
                }
            }
            emitSource(source)
        }
    }

    override fun comments(postId: Int): LiveData<ResponseData<Comments>> = liveData {
        if(existComments(postId)){
            emit(localeComments(postId))
        }else{
            val source  = Transformations.map(tempComments(postId)) {
                when (it) {
                    is ResponseData.Success -> {
                        val comments = it.requireData()
                        val pairedComments = Pair(postId, comments)
                        saveComments(pairedComments)
                        it
                    }
                    else -> it
                }
            }
            emitSource(source)
        }
    }

    //comments
    fun existComments(postId:Int):Boolean{
        return commentsDao.exists(postId)
    }

    fun tempComments(postId:Int) = call {
        feedApi.comments(postId)
    }

    fun saveComments(comments:Pair<Int, Comments>){
        val result = comments.second.toEntities(comments.first)
        commentsDao.insert(result)
    }

    fun localeComments(postId: Int):ResponseData<Comments>{
        val comments = commentsDao.commentsSync(postId).map { it.toComment() }
        return ResponseData.Success(comments)
    }


    //user
    fun getUserIdByPostId(postId: Int):Int{
        if(existPost(postId)){
            return postsDao.postSync(postId).post.userId
        }else{
            throw NullPointerException()
        }
    }

    fun existUser(id:Int):Boolean{
        return userDao.exists(id)
    }

    fun tempUser(id:Int) = call {
        feedApi.user(id)
    }

    fun saveUser(user: User){
        userDao.insert(user.toEntity())
    }

    fun localeUser(id: Int):ResponseData<User>{
        val user = userDao.userSync(id).user
        return ResponseData.Success(user)
    }

    fun existPost(postId: Int):Boolean{
        return postsDao.isExist(postId)
    }
    //posts

    fun existPosts():Boolean{
        return postsDao.isExists()
    }

    fun tempPosts() = call {
        feedApi.posts()
    }

    fun savePosts(posts: Posts){
        postsDao.clear()
        postsDao.insert(posts.toEntities())
    }

    fun localePost(postId: Int):ResponseData<Post>{
        val post = postsDao.postSync(postId).post
        return ResponseData.Success(post)
    }

    fun localePosts():ResponseData<Posts>{
        val posts = postsDao.allSync().map { it.post }
        return ResponseData.Success(posts)
    }
}
