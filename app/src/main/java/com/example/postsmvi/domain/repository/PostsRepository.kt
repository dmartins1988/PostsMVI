package com.example.postsmvi.domain.repository

import com.example.postsmvi.domain.Post

interface PostsRepository {
    suspend fun getAllPosts(): List<Post>
    suspend fun getPostsPaginated(limit: Int, offset: Int): Result<List<Post>>
    suspend fun savePosts(listOfPosts: List<Post>)
}