package com.example.composeroomretrofitmvi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val postId:Int=0,
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val timestamp: Long = System.currentTimeMillis()
)