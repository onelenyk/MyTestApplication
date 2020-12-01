package com.example.myapplication.ui.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.FeedFragmentBinding
import com.example.myapplication.databinding.PostFragmentBinding
import com.example.myapplication.ui.base.BaseLoadingFragment
import com.example.myapplication.ui.feed.FeedAdapter
import com.example.myapplication.ui.feed.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.post_fragment.*

@AndroidEntryPoint
class PostFragment : BaseLoadingFragment() {
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override val viewModel by viewModels<PostViewModel>()

    private var binding: PostFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PostFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        this.binding = binding

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComment()
        setupView()
        load()
    }

    fun load(){
        val postId = requireArguments().getInt("postId")
        viewModel.loadData(postId)
    }

    fun setupView(){
        binding?.viewModel = viewModel
    }

    fun setupComment(){
        binding?.rvComments?.adapter = commentAdapter

        viewModel.comments.observe(viewLifecycleOwner, Observer {
            commentAdapter.start(it)
        })
    }

}