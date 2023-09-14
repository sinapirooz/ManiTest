package com.example.manitest.data.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)