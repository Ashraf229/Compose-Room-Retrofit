package com.example.composeroomretrofitmvi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeroomretrofitmvi.data.local.dao.PostDao
import com.example.composeroomretrofitmvi.data.local.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun postDao(): PostDao
}