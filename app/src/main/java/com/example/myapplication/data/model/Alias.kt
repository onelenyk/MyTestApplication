package com.example.myapplication.data.model

typealias Comments = List<Comment>
typealias Posts = List<Post>
typealias Users = List<User>

fun Comments.toEntities(post: Post):List<CommentsEntity>{
    return this.map { CommentsEntity(postId = post.id, comment = it) }
}

fun Posts.toEntities():List<PostEntity>{
    return this.map { it.toEntity() }
}
