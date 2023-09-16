package com.example.manitest.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

)
