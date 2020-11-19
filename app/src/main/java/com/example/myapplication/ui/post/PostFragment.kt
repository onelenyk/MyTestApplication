package com.example.myapplication.ui.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.ui.feed.FeedAdapter
import com.example.myapplication.ui.feed.FeedViewModel
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.post_fragment.*

class PostFragment : Fragment(R.layout.post_fragment) {


    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }


    private val viewModel: PostViewModel by lazy {  ViewModelProvider(this).get(
        PostViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComment()
        setupPost()
        load()
    }

    fun load(){
        val postId = requireArguments().getInt("postId")
        viewModel.loadData(postId)
    }

    fun setupPost(){
        viewModel.user.observe(viewLifecycleOwner, Observer {
            tv_user.text = it.username
        })

        viewModel.post.observe(viewLifecycleOwner, Observer {
            tv_title.text = it.title
            tv_body.text = it.body
        })
    }

    fun setupComment(){
        rv_comments.adapter = commentAdapter

        viewModel.comments.observe(viewLifecycleOwner, Observer {
            commentAdapter.start(it)
        })
    }

}