package com.example.manitest.data.repository

import com.example.manitest.data.model.MovieDetail
import com.example.manitest.vo.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeDetailRepository: DetailRepository {

    val movieDetail = MovieDetail(
        title = "Test Movie",
        overview = "This is a test movie",
        genres = emptyList(),
        posterPath = null,
        backdropPath = null,
        originalLanguage = "en",
        releaseD_date = "2023-09-30",
        voteAverage = 7.5f,
        voteCount = 100
    )

    private val movieFlow = MutableSharedFlow<Resource<MovieDetail>>()
    override fun getMovieFlow(): MutableSharedFlow<Resource<MovieDetail>> {
        return movieFlow

    }


    override suspend fun getMovieDetail(id: Int) {
        movieFlow.emit(Resource.success(movieDetail))
    }


}