package com.example.composeroomretrofitmvi.navigation

import com.example.composeroomretrofitmvi.domain.model.Post
import kotlinx.serialization.Serializable

sealed interface ScreenList {
    @Serializable
    data object HomeScreen: ScreenList

    @Serializable
    data class DetailScreen(val post: Post): ScreenList
}