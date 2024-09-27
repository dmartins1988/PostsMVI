package com.example.postsmvi.presentation

import com.example.postsmvi.domain.Post

data class PostUiState(
    val pageNumber: Int = 0,
    val list: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    var errorMessage: String? = null,
    val endReached: Boolean = false
)
