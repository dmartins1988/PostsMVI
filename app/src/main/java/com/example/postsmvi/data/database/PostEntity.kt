package com.example.postsmvi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Posts"
)
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)
