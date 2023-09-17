package com.example.manitest.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.manitest.R
import com.example.manitest.data.model.Movie
import com.example.manitest.ui.component.ErrorMessage
import com.example.manitest.ui.component.Header
import com.example.manitest.ui.component.Loading
import com.example.manitest.ui.screen.destinations.DetailScreenDestination
import com.example.manitest.ui.theme.backgroundColor
import com.example.manitest.ui.theme.cardColor
import com.example.manitest.ui.theme.onBackgroundColor
import com.example.manitest.ui.viewmodel.ListingViewModel
import com.example.manitest.util.PathHandler
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Destination(start = true)
@Composable
fun ListingScreen(
    viewModel: ListingViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val movies: LazyPagingItems<Movie> = viewModel.getPopularMovies().collectAsLazyPagingItems()


    Column(modifier = Modifier.background(backgroundColor)) {
        Header(modifier = Modifier.padding(bottom = 16.dp), title = "Popular")
        MoviesGrid(
            movieList = movies,
            navigator
        )
    }

}


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MoviesGrid(
    movieList: LazyPagingItems<Movie>,
    navigator: DestinationsNavigator
) {

    if (movieList.loadState.refresh == LoadState.Loading) {
        Loading()
    } else if (movieList.loadState.refresh is LoadState.Error) {
        ErrorMessage(onBackgroundColor)
    }

    val scrollState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = Modifier.background(backgroundColor),
        columns = GridCells.Fixed(3),
        state = scrollState,
        content = {

            items(movieList.itemCount) { index ->

                val movie = movieList[index]

                if (movie != null) {
                    ListingItem(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                            .aspectRatio(.5f)
                            .clickable { navigator.navigate(DetailScreenDestination(movieId = movie.id)) },
                        movie
                    )
                }

            }
        })
}


@Composable
fun ListingItem(
    modifier: Modifier,
    movie: Movie
) {

    Column(
        modifier = modifier
            .background(cardColor)
            .height(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            PathHandler.getImageUrlForPoster(movie.posterPath),
            error = painterResource(R.drawable.poster_container),
            placeholder = painterResource(R.drawable.poster_container),
            contentDescription = "MoviePoster",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Text(
            text = movie.title,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            maxLines = 2,
            fontSize = 12.sp,
            color = Color.White,
            overflow = TextOverflow.Ellipsis
        )
    }

}


@Preview
@Composable
fun PreviewListingItem() {
    MaterialTheme {
        ListingItem(
            modifier = Modifier.size(width = 80.dp, height = 180.dp),
            Movie(
                0,
                "Something",
                "https://s100.divarcdn.com/static/photo/post/0cQTEB_nA-6WCcS00fyHFQ/678e5c4e-bb31-403b-93c5-0e3600f9cae0.jpg"
            )

        )
    }
}