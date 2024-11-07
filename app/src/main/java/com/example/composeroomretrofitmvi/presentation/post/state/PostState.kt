package com.example.composeroomretrofitmvi.presentation.post.state

import com.example.composeroomretrofitmvi.domain.model.Post

data class PostState(
    val error: String = "",
    val loading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val selectedPost: Post = Post("", 0, "", 0),
    val searchQuery: String = "",
    val searchExpand:Boolean = false
)
