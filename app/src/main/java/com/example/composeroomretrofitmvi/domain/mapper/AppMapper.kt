package com.example.composeroomretrofitmvi.domain.mapper

import com.example.composeroomretrofitmvi.data.local.entities.PostEntity
import com.example.composeroomretrofitmvi.data.remote.dto.PostDTO
import com.example.composeroomretrofitmvi.domain.model.Post

//Todo : For Insert PostDto to PostEntity
fun PostDTO.toPostEntityForInsert(): PostEntity {
    return PostEntity(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}

//Todo : Delete Post from PostEntity
fun Post.toPostEntityForDelete(): PostEntity {
    return PostEntity(
        title = title,
        body = body,
        userId = userId,
        id = id,
        postId = postId
    )
}

//Todo : To get all Post from PostEntity
fun PostEntity.toPost(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        postId = postId,
        userId = userId
    )
}
