package com.example.composeroomretrofitmvi.data.repository

import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.util.Log
import com.example.composeroomretrofitmvi.data.local.dao.PostDao
import com.example.composeroomretrofitmvi.data.remote.service.PostApi
import com.example.composeroomretrofitmvi.domain.mapper.toPost
import com.example.composeroomretrofitmvi.domain.mapper.toPostEntityForInsert
import com.example.composeroomretrofitmvi.domain.model.Post
import com.example.composeroomretrofitmvi.domain.repository.PostRepository
import com.example.composeroomretrofitmvi.domain.usecase.Resource
import com.example.composeroomretrofitmvi.domain.usecase.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao,
    private val connectivityManager: ConnectivityManager,
    private val preferences: SharedPreferences
) : PostRepository {
    override suspend fun getPost(): Flow<Resource<List<Post>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val today = Utils.getCurrentDate()
                val lastFetchDate = preferences.getString(LAST_FETCH_DATE_KEY, null)
                Log.e("RepoTag", "Called1" )
                if (Utils.isInternetAvailable(connectivityManager) && today != lastFetchDate) {
                    Log.e("RepoTag", "Called2" )
                    dao.deleteAllPostsFromDB()

                    api.getPosts().map {
                        dao.upsertPostInDB(it.toPostEntityForInsert())
                    }
                    emit(Resource.Error(message = ""))

                    preferences.edit().putString(LAST_FETCH_DATE_KEY, today).apply()

                }
                withTimeout(5000L){
                    emit(Resource.Loading(false))
                }

                dao.getAllPostFromDB().collect { postDB ->
                    if (postDB.isEmpty()) {
                        emit(Resource.Error(message = "Error in loading Posts"))
                    } else{
                        emit(Resource.Success(postDB.map { it.toPost() }))
                        Log.e("RepoTag", "Called3" )
                    }
                }


            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error in loading Posts"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error in loading Posts"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading Posts"))
                return@flow
            } finally {
                emit(Resource.Loading(false))
            }
        }
    }


    companion object {
        const val LAST_FETCH_DATE_KEY = "last_fetch_date"
    }
}