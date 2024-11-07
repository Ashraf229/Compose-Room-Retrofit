package com.example.composeroomretrofitmvi.di.module

import android.content.Context
import androidx.room.Room
import com.example.composeroomretrofitmvi.data.local.dao.PostDao
import com.example.composeroomretrofitmvi.data.local.database.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDB {
        return Room.databaseBuilder(
            appContext,
            AppDB::class.java,
            "NotesDB.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDB) : PostDao{
        return appDatabase.postDao()
    }
}