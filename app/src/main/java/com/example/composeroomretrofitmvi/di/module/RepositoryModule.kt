package com.example.composeroomretrofitmvi.di.module

import com.example.composeroomretrofitmvi.data.repository.PostRepositoryImpl
import com.example.composeroomretrofitmvi.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl):PostRepository

}