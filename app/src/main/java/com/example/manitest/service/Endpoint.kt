package com.example.manitest.service

import com.example.manitest.data.model.MovieDetail
import com.example.manitest.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    companion object {
        const val API_URL = "https://api.themoviedb.org/"
    }

    @GET("3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc")
    suspend fun getMostPopularMovies(
        @Query("page") page: Int,
    ): Movies

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int
    ): MovieDetail

}