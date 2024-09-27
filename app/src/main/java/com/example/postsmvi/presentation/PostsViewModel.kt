package com.example.postsmvi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsmvi.domain.Post
import com.example.postsmvi.domain.repository.PostsRepository
import com.example.postsmvi.paging.DefaultPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class PostsViewModel(
    private val repository: PostsRepository
) : ViewModel(), KoinComponent {

    companion object {
        private const val MAX_PAGE_SIZE = 20
    }

    private val _state = MutableStateFlow(PostUiState())
    val state = _state.asStateFlow()

    private val pagination = DefaultPaging(
        initialKey = _state.value.pageNumber,
        onLoadUpdated = { status ->
            _state.update {
                it.copy(
                    isLoading = status
                )
            }
        },
        onRequest = { _ ->
            repository.getPostsPaginated(
                limit = MAX_PAGE_SIZE,
                offset = _state.value.pageNumber * MAX_PAGE_SIZE
            )
        },
        getNextKey = {
            _state.value.pageNumber + 1
        },
        onError = { throwable ->
            _state.update {
                it.copy(
                    errorMessage = throwable?.localizedMessage
                )
            }
        },
        onSuccess = { items, newKey ->
            _state.update {
                it.copy(
                    list = it.list + items,
                    pageNumber = newKey,
                    endReached = items.isEmpty()
                )
            }
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            delay(3000)
            pagination.loadNextItems()
        }
    }

    init {
        loadNextItems()
    }

    fun addDummyData() {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = repository.getAllPosts()
            if (posts.isNotEmpty()) {
                return@launch
            }
            val list: ArrayList<Post> = ArrayList()
            for (i in 0..100) {
                list.add(
                    Post(
                        id = i,
                        title = "Title $i",
                        description = "Description $i"
                    )
                )
            }
            repository.savePosts(list)
        }
    }

}