package com.example.myapplication.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.CommentItem
import com.example.myapplication.data.model.PostItem

class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    val data = mutableListOf<CommentItem>()

    fun start(list:List<CommentItem>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class CommentViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val postTitle = view.findViewById<TextView>(R.id.tv_username)
        private val postBody = view.findViewById<TextView>(R.id.tv_body)

        fun bind(item: CommentItem){
            with(item){
                postTitle.text = comment.name
                postBody.text = comment.body
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_rv_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(data[position])
    }
}