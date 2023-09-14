package com.example.manitest.ui.viewmodel

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ListingViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle,
                                           private val listingRepository: ListingRepository) :
    ViewModel() {
    fun getPopularMovies(): Flow<PagingData<Movie>> =
        listingRepository.getMovies().cachedIn(viewModelScope)

}