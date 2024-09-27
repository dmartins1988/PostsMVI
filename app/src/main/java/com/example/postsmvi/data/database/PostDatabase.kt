package com.example.postsmvi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class PostDatabase : RoomDatabase() {

    abstract val postDao: PostDao

    companion object {
        const val DATABASE_NAME = "post_db"
    }
}