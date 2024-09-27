package com.example.postsmvi.domain

import com.example.postsmvi.data.database.PostEntity

fun PostEntity.toPost(): Post {
    return Post(
        id = id,
        title = title,
        description = description
    )

}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        title = title,
        description = description
    )
}