package com.example.postsmvi.data.repository

import com.example.postsmvi.data.database.PostDao
import com.example.postsmvi.domain.Post
import com.example.postsmvi.domain.repository.PostsRepository
import com.example.postsmvi.domain.toPost
import com.example.postsmvi.domain.toPostEntity

class PostsRepositoryImpl(
    private val postDao: PostDao
) : PostsRepository {
    override suspend fun getAllPosts(): List<Post> {
        return postDao.getAllPosts().map { it.toPost() }
    }

    override suspend fun getPostsPaginated(limit: Int, offset: Int): Result<List<Post>> {
        return try {
            Result.success(postDao.getPostsPaginated(limit, offset).map { it.toPost() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun savePosts(listOfPosts: List<Post>) {
        postDao.savePosts(listOfPosts.map { it.toPostEntity() })
    }
}