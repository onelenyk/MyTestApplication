package com.example.myapplication.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.model.PostItem
import com.example.myapplication.databinding.FeedFragmentBinding
import com.example.myapplication.ui.base.BaseLoadingFragment
import com.example.myapplication.ui.base.BaseLoadingViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FeedFragment : BaseLoadingFragment(), OnFeedListener{

    override val viewModel by viewModels<FeedViewModel>()

    private val feedAdapter:FeedAdapter by lazy {
        FeedAdapter(this)
    }

    private var binding:FeedFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FeedFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        this.binding = binding

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFeed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun setupFeed(){
        binding?.rvFeed?.adapter = feedAdapter

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