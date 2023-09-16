package com.example.manitest.data.repository

import com.example.manitest.data.model.MovieDetail
import com.example.manitest.service.Endpoint
import com.example.manitest.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepositoryDefault @Inject constructor(private val client: Endpoint): DetailRepository {

    private val movieFlow = MutableSharedFlow<Resource<MovieDetail>>()

    override suspend fun getMovieDetail(id: Int) {
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

    override fun getMovieFlow(): MutableSharedFlow<Resource<MovieDetail>> {
        return movieFlow
    }
}