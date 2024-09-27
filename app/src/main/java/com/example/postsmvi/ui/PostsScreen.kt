package com.example.postsmvi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.postsmvi.presentation.PostsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<PostsViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        viewModel.addDummyData()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(state.list.size) { i ->
            val post = state.list[i]
            if (i >= state.list.size - 1 && state.endReached.not() && state.isLoading.not()) {
                viewModel.loadNextItems()
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = post.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}