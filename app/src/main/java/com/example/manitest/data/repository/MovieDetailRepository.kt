package com.example.manitest.data.repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.manitest.data.MoviesPagingSource
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.service.Endpoint
import com.example.manitest.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val client: Endpoint) {

    val movieFlow = MutableSharedFlow<Resource<MovieDetail>>()

    suspend fun getMovieDetail(id: Int) {
        movieFlow.emit(Resource.loading(null))

        withContext(Dispatchers.IO) {
            try {
                val result = client.getMovieDetail(id)
                movieFlow.emit(Resource.success(result))
            } catch (e: Exception) {
                movieFlow.emit(Resource.error("Error while fetching item",null))
                e.printStackTrace()
            }
        }

    }
}