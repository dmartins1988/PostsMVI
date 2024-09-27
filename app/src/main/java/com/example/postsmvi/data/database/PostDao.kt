package com.example.postsmvi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(listOfPost: List<PostEntity>)

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM posts LIMIT :limit OFFSET :offset")
    suspend fun getPostsPaginated(
        limit: Int,
        offset: Int
    ): List<PostEntity>
}