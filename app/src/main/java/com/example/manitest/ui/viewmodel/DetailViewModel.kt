package com.example.manitest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.data.repository.DetailRepository
import com.example.manitest.data.repository.MovieDetailRepositoryDefault
import com.example.manitest.di.AppModule
import com.example.manitest.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    @AppModule.DefaultDetailRepository private val detailRepository: DetailRepository
) : ViewModel() {

    var movieFlow: MutableStateFlow<Resource<MovieDetail>> =
        MutableStateFlow(Resource.notSetup(null, null))

    init {

        viewModelScope.launch {
            detailRepository.getMovieFlow().collect {
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
