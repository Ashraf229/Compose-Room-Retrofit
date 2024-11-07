package com.example.composeroomretrofitmvi.presentation.post.event

import com.example.composeroomretrofitmvi.domain.model.Post

sealed interface PostEvent {
    data class Navigate(val selectedPost:Post) : PostEvent
    data object RefreshPost : PostEvent
    data class SearchPost(val query: String) : PostEvent
    data object ToggleSearchExpand : PostEvent
    data object ClearSearch : PostEvent

}