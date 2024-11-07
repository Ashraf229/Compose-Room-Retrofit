package com.example.composeroomretrofitmvi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.composeroomretrofitmvi.data.local.entities.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Upsert
    suspend fun upsertPostInDB(post: PostEntity)

    @Delete
    suspend fun deleteNoteFromDB(note: PostEntity)

    @Query("SELECT * FROM PostEntity")
    fun getAllPostFromDB(): Flow<List<PostEntity>>

    @Query("DELETE FROM PostEntity")
    suspend fun deleteAllPostsFromDB()

}