package com.example.myapplication.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.PostItem
import com.example.myapplication.databinding.PostRvItemBindingBinding

class FeedAdapter(val onFeedListener: OnFeedListener) : RecyclerView.Adapter<FeedAdapter.PostViewHolder>(){

    val data = mutableListOf<PostItem>()

    fun start(list:List<PostItem>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class PostViewHolder(val binding:PostRvItemBindingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: PostItem, onFeedListener: OnFeedListener){
            binding.item = item
            binding.listener = onFeedListener
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  PostRvItemBindingBinding.inflate(inflater, parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(data[position],onFeedListener)
    }
}