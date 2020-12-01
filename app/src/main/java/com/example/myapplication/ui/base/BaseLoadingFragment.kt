package com.example.myapplication.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.ui.feed.FeedViewModel

abstract class BaseLoadingFragment: Fragment(){

    @IdRes
    open fun getProgressId():Int =
        R.id.view_progressBar

    var progressBar: ProgressBar? = null

    enum class BaseState{
        PROGRESS_TRUE,
        PROGRESS_FALSE
    }

    abstract val viewModel:BaseLoadingViewModel

    private var loadIncrement = 0

    fun showLoad() {
        loadIncrement += 1
        loading(BaseState.PROGRESS_TRUE)
    }

    fun hideLoad() {
        if(loadIncrement > 0) {
            loadIncrement -= 1
        }
        loading(BaseState.PROGRESS_FALSE)
    }

    open fun loading(state: BaseState) {
        val s = state == BaseState.PROGRESS_TRUE
        safeLoading(if(!(!s && loadIncrement == 0)){
            View.VISIBLE
        }else{
            View.GONE
        })
    }

    private fun safeLoading(value:Int){
        if(isDetached) {
            Log.d(
                "View",
                "View is detached, skip loader new value posting"
            )
        }else{
            progressBar?.visibility = value
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(getProgressId())
        observeLoading()
        observeToasting()
    }

    protected fun observeToasting(){
        viewModel.toaster.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
    private fun observeLoading(){
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it == BaseState.PROGRESS_TRUE){
                showLoad()
            }else{
                hideLoad()
            }
        })
    }

}