package com.example.composeroomretrofitmvi.domain.repository

import com.example.composeroomretrofitmvi.domain.model.Post
import com.example.composeroomretrofitmvi.domain.usecase.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost():Flow<Resource<List<Post>>>
}