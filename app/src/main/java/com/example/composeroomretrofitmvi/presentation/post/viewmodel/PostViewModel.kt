package com.example.composeroomretrofitmvi.presentation.post.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeroomretrofitmvi.data.repository.PostRepositoryImpl.Companion.LAST_FETCH_DATE_KEY
import com.example.composeroomretrofitmvi.domain.repository.PostRepository
import com.example.composeroomretrofitmvi.domain.usecase.Resource
import com.example.composeroomretrofitmvi.presentation.post.event.PostEvent
import com.example.composeroomretrofitmvi.presentation.post.state.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository,
    private val preferences: SharedPreferences
) : ViewModel() {
    private val _state = MutableStateFlow(PostState())
    val state = _state.asStateFlow()

    init {
        getPosts()
    }

    fun onEvent(event: PostEvent) {
        when (event) {
            is PostEvent.Navigate -> {
                _state.update {
                    it.copy(
                        selectedPost = event.selectedPost
                    )
                }

            }

            is PostEvent.RefreshPost -> {
                preferences.edit().putString(LAST_FETCH_DATE_KEY, null).apply()
                getPosts()
            }

            is PostEvent.SearchPost -> {
                _state.update { it.copy(searchQuery = event.query) }
                getPosts()
            }

            is PostEvent.ToggleSearchExpand -> {
                _state.update { it.copy(searchExpand = !_state.value.searchExpand) }
            }

            PostEvent.ClearSearch -> {
                _state.update { it.copy(searchQuery = "") }
                getPosts()
            }
        }
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPost().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message!!
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                loading = result.isLoading
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { postList ->
                            val filteredPosts = postList.filter { post ->
                                post.title.contains(_state.value.searchQuery, ignoreCase = true)
                            }

                            _state.update {
                                it.copy(
                                    posts = filteredPosts
                                )
                            }
                        }
                    }
                }

            }
        }
    }


}