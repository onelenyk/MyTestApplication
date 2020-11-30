package com.example.myapplication.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.CommentItem
import com.example.myapplication.data.model.PostItem
import com.example.myapplication.databinding.CommentRvItemBindingBinding
import com.example.myapplication.databinding.PostRvItemBindingBinding
import com.example.myapplication.ui.feed.FeedAdapter

class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    val data = mutableListOf<CommentItem>()

    fun start(list:List<CommentItem>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class CommentViewHolder(val binding :CommentRvItemBindingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CommentItem){
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  CommentRvItemBindingBinding.inflate(inflater, parent,false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(data[position])
    }
}