package com.example.manitest.ui

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.manitest.data.model.Movie
import com.example.manitest.ui.viewmodel.ListingViewModel
import com.example.manitest.util.PathHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ListingScreen(
    viewModel: ListingViewModel = viewModel()
) {


    val movies = viewModel.getPopularMovies().collectAsLazyPagingItems()

    MoviesGrid(
        movieList = movies
    )

}


@Composable
fun MoviesGrid(
    movieList: LazyPagingItems<Movie>
) {
    Log.d("movieList", movieList.itemCount.toString())
    if (movieList.itemCount > 0)
        Log.d("movieList", movieList[0]?.title.toString())
    Log.d("MoviesGrid", movieList.toString())
    Log.d("MoviesGrid", movieList.loadState.toString())

    LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
        items(movieList.itemCount) { index ->

            val movie = movieList[index]

            if (movie != null) {
                ListingItem(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .aspectRatio(.5f),
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
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    Column(
        modifier = modifier
            .background(Color.Black)
            .height(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(

            model = ImageRequest.Builder(LocalContext.current)
                .data(PathHandler.getImageUrlForPoster(movie.posterPath))

                .build(),
            contentDescription = "MoviePoster",
            modifier = Modifier
                .fillMaxWidth(), imageLoader = imageLoader

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

//@Preview
//@Composable
//fun PreviewMoviesGrid() {
//    MaterialTheme {
//        MoviesGrid(
//            movieList = listOf(
//                Movie("SomeThing", "/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg"),
//                Movie("SomeThing Else", "/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg")
//            )
//        )
//    }
//}

@Preview
@Composable
fun PreviewListingItem() {
    MaterialTheme {
        ListingItem(
            modifier = Modifier.size(width = 80.dp, height = 180.dp),
            Movie(
                "Something",
                "https://s100.divarcdn.com/static/photo/post/0cQTEB_nA-6WCcS00fyHFQ/678e5c4e-bb31-403b-93c5-0e3600f9cae0.jpg"
            )

        )
    }
}