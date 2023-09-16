package com.example.manitest.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val title: String,
    val overview: String,

    val genres: List<Genre>,


    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("release_date")
    val releaseD_date: String,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int

)
