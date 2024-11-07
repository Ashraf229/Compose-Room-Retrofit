package com.example.composeroomretrofitmvi.data.remote.service

import com.example.composeroomretrofitmvi.data.remote.dto.PostDTO
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts() : List<PostDTO>
}