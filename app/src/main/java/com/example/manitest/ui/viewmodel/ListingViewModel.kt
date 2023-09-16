package com.example.manitest.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.manitest.data.model.Movie
import com.example.manitest.data.repository.ListingRepository
import com.example.manitest.vo.ListState
import com.example.manitest.vo.Status
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.manitest.data.MoviesPagingSource
import com.example.manitest.service.Endpoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository
) :
    ViewModel() {

    init {
        Log.d("ListingViewModel", "created")
    }

    private val pager = repository.getPager().flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)

    fun getPopularMovies(): Flow<PagingData<Movie>> =
        pager





}