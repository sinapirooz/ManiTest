package com.example.manitest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.manitest.data.MoviesPagingSource
import com.example.manitest.data.model.Movie
import com.example.manitest.data.model.Movies
import com.example.manitest.service.Endpoint
import com.example.manitest.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ListingRepository @Inject constructor (private val client: Endpoint) {
    fun getMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(client)
        }
    ).flow
}