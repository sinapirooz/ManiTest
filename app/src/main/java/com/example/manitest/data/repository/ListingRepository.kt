package com.example.manitest.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.manitest.data.MoviesPagingSource
import com.example.manitest.service.Endpoint
import javax.inject.Inject


class ListingRepository @Inject constructor (private val client: Endpoint) {

    private val pager  = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(client)
        }
    )


    fun getPager() = pager
}