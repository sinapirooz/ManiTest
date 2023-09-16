package com.example.manitest.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.manitest.data.repository.FakeDetailRepository
import com.example.manitest.role.MainDispatcherRule
import com.example.manitest.vo.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class DetailViewModelTest {
    private lateinit var detailRepository: FakeDetailRepository
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setupDetailViewModel() {
        detailRepository = FakeDetailRepository()
        detailViewModel = DetailViewModel(detailRepository)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRole = InstantTaskExecutorRule()

    @Test
    fun movieFlow_successResponse() {

        detailViewModel.getDetail(0)

        runBlockingTest {
            delay(500)

            MatcherAssert.assertThat(
                detailViewModel.movieFlow.value.status == Status.SUCCESS, Matchers.`is`(true)
            )

        }


    }


}