package com.example.manitest.service

import com.example.manitest.data.model.Movie
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.data.model.Movies
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    companion object {
        const val API_URL = "https://api.themoviedb.org/"

        const val SUCCESS_RESPONSE_CODE = 200
        const val CONNECT_TIMEOUT: Long = 0
        const val CALL_TIMEOUT: Long = 0
        const val READ_TIMEOUT: Long = 0
        const val WRITE_TIMEOUT: Long = 0
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