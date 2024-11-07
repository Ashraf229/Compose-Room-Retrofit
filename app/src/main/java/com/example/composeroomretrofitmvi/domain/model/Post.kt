package com.example.composeroomretrofitmvi.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val postId:Int=0
)