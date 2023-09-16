package com.example.manitest.data.repository

import com.example.manitest.data.model.MovieDetail
import com.example.manitest.vo.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

interface DetailRepository {
    fun getMovieFlow(): MutableSharedFlow<Resource<MovieDetail>>
    suspend fun getMovieDetail(id: Int)
}