package com.example.myapplication.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.model.PostItem
import kotlinx.android.synthetic.main.feed_fragment.*


class FeedFragment : Fragment(R.layout.feed_fragment), OnFeedListener{

    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this).get(FeedViewModel::class.java)
    }

    private val feedAdapter:FeedAdapter by lazy {
        FeedAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFeed()
    }

    fun setupFeed(){
        rv_feed.adapter = feedAdapter

        viewModel.feedList.observe(viewLifecycleOwner, Observer {
            feedAdapter.start(it)
        })
    }

    override fun postClick(postItem: PostItem) {
        findNavController().navigate(
            R.id.action_feedFragment_to_postFragment,
            bundleOf("postId" to postItem.post.id))
    }

}