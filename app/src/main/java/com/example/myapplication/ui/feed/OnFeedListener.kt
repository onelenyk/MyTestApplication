package com.example.myapplication.ui.feed

import com.example.myapplication.data.model.PostItem

interface OnFeedListener{
    fun postClick(postItem: PostItem)
}