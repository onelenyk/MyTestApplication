package com.example.myapplication.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.PostItem

class FeedAdapter(val onFeedListener: OnFeedListener) : RecyclerView.Adapter<FeedAdapter.PostViewHolder>(){

    val data = mutableListOf<PostItem>()

    fun start(list:List<PostItem>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class PostViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val postTitle = view.findViewById<TextView>(R.id.tv_title)
        private val postBody = view.findViewById<TextView>(R.id.tv_body)

        fun bind(item: PostItem, onFeedListener: OnFeedListener){
            with(item){
                postTitle.text = post.title
                postBody.text = post.body
            }

            itemView.setOnClickListener{
                onFeedListener.postClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_rv_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(data[position],onFeedListener)
    }
}