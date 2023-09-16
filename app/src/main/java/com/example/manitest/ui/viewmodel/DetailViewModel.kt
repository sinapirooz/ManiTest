package com.example.manitest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.data.repository.ListingRepository
import com.example.manitest.data.repository.MovieDetailRepository
import com.example.manitest.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: MovieDetailRepository
) : ViewModel() {

    var movieFlow: MutableStateFlow<Resource<MovieDetail>> = MutableStateFlow(Resource.notSetup(null,null))

    init {

            viewModelScope.launch {
                detailRepository.movieFlow.collect{
                    movieFlow.value = it
                }
            }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            detailRepository.getMovieDetail(id)
        }
    }

}
