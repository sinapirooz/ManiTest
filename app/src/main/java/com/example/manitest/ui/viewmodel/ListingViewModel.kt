package com.example.manitest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.manitest.data.model.Movie
import com.example.manitest.data.repository.ListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository
) :
    ViewModel() {


    private val pager = repository.getPager().flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)

    fun getPopularMovies(): Flow<PagingData<Movie>> =
        pager
}