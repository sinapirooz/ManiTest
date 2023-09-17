package com.example.manitest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.data.repository.DetailRepository
import com.example.manitest.di.AppModule
import com.example.manitest.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    @AppModule.DefaultDetailRepository private val detailRepository: DetailRepository
) : ViewModel() {

    private val _movieFlow: MutableStateFlow<Resource<MovieDetail>> =
        MutableStateFlow(Resource.notSetup(null, null))

    val movieFlow = _movieFlow.asStateFlow()

    init {
        viewModelScope.launch {
            detailRepository.getMovieFlow().collect {
                _movieFlow.value = it
            }
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            detailRepository.getMovieDetail(id)
        }
    }

}
