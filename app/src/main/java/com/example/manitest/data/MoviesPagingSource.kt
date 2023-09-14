package com.example.manitest.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.manitest.data.model.Movie
import com.example.manitest.service.Endpoint


class MoviesPagingSource (private val endpoint: Endpoint) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            Log.d("loadload","try")
            val page = params.key ?: 1
            val response = endpoint.getMostPopularMovies(page = page)

            Log.d("loadload","response")


            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("loadload","catch")

            LoadResult.Error(e)
        }
    }

}