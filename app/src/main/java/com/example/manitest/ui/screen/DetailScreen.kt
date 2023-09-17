package com.example.manitest.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.manitest.R
import com.example.manitest.data.model.Genre
import com.example.manitest.data.model.MovieDetail
import com.example.manitest.ui.component.ErrorMessage
import com.example.manitest.ui.component.Loading
import com.example.manitest.ui.theme.backgroundColor
import com.example.manitest.ui.theme.onSurfaceColor
import com.example.manitest.ui.theme.surfaceColor
import com.example.manitest.ui.viewmodel.DetailViewModel
import com.example.manitest.util.PathHandler
import com.example.manitest.vo.Resource
import com.example.manitest.vo.Status
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.math.RoundingMode
import java.text.DecimalFormat

@ExperimentalCoroutinesApi
@Destination
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    movieId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    movieFlow: State<Resource<MovieDetail>> = viewModel.movieFlow.collectAsState()

) {

    if (movieFlow.value.status != Status.SUCCESS)
        viewModel.getDetail(movieId)

    Surface(modifier = Modifier
        .background(surfaceColor)
        .fillMaxSize()) {
        when (movieFlow.value.status) {
            Status.SUCCESS -> {
                Column(modifier = Modifier
                    .background(surfaceColor)
                    .fillMaxSize()) {
                    movieFlow.value.data?.let { TopSection(movieDetail = it, onBackClicked = {navigator.popBackStack()}) }
                    Spacer(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(backgroundColor)
                    )
                    movieFlow.value.data?.let { Specs(movieDetail = it) }
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(backgroundColor)
                    )
                    movieFlow.value.data?.let { Overview(text = it.overview) }
                }
            }

            Status.LOADING, Status.NOT_SETUP -> {
                Loading()
            }

            Status.ERROR -> {
                ErrorMessage(onSurfaceColor)
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopSection(movieDetail: MovieDetail, onBackClicked: (() -> Unit)) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (backdrop, poster, genres, title) = createRefs()

        val endGuideline = createGuidelineFromStart(0.34f)

        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .constrainAs(backdrop) {}) {
            AsyncImage(
                model = PathHandler.getImageUrlForaBackdrop(movieDetail.backdropPath),
                contentDescription = "",
                error = painterResource(R.drawable.image_not_found),
                placeholder = painterResource(R.drawable.image_not_found),
                modifier = Modifier.fillMaxWidth()
            )

        }
        AsyncImage(
            model = PathHandler.getImageUrlForaBackdrop(movieDetail.posterPath),
            contentDescription = "",
            error = painterResource(R.drawable.poster_container),
            placeholder = painterResource(R.drawable.poster_container),
            modifier = Modifier
                .height(180.dp)
                .constrainAs(poster) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(backdrop.bottom, margin = (-50).dp)
                    end.linkTo(endGuideline)
                }, // this is the modifier you passed to SubcomposeAsyncImage
        )

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 16.dp)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                modifier = Modifier
                    .clickable {onBackClicked()},
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = surfaceColor
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .clickable {},
                imageVector = Icons.Filled.Share,
                contentDescription = "",
                tint = surfaceColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                modifier = Modifier
                    .clickable {},
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "",
                tint = surfaceColor
            )
        }
        Text(

            text = movieDetail.title,
            modifier = Modifier
                .fillMaxWidth(.6f)
                .wrapContentHeight()
                .constrainAs(title) {
                    start.linkTo(endGuideline, margin = 16.dp)
                    top.linkTo(backdrop.bottom, margin = 16.dp)

                },

            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            color = backgroundColor
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .constrainAs(genres) {
                    start.linkTo(endGuideline, margin = 16.dp)
                    top.linkTo(title.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
        ) {
            FlowRow(

                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                repeat(movieDetail.genres.size) { index ->
                    GenreItem(item = movieDetail.genres.get(index))
                }
            }

        }
    }
}

@Composable
fun Specs(
    movieDetail: MovieDetail
) {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    val shortenedRate = df.format(movieDetail.voteAverage)


    Row {
        SpecItem(
            modifier = Modifier.weight(1f),
            text = shortenedRate.toString(),
            subText = "${movieDetail.voteCount} votes",
            icon = Icons.Filled.Star,
            direction = LayoutDirection.Ltr
        )
        SpecItem(
            modifier = Modifier.weight(1f),
            text = movieDetail.originalLanguage,
            subText = "Language",
            icon = Icons.Outlined.Language,
            direction = LayoutDirection.Rtl
        )
        SpecItem(
            modifier = Modifier.weight(1f),
            text = movieDetail.releaseD_date,
            subText = "Release Date",
            icon = Icons.Filled.AvTimer,
            direction = LayoutDirection.Rtl
        )
    }
}

@Composable
fun GenreItem(
    modifier: Modifier = Modifier,
    item: Genre
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .border(
                1.dp, backgroundColor, RoundedCornerShape(30.dp)
            )
    ) {
        Text(
            text = item.name,
            color = backgroundColor,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(4.dp)
                .padding(horizontal = 3.dp, vertical = 3.dp)
                .wrapContentWidth(),
            textAlign = TextAlign.Center

        )
    }

}

@Composable
fun SpecItem(
    modifier: Modifier = Modifier,
    text: String,
    subText: String,
    icon: ImageVector,
    direction: LayoutDirection
) {
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = text, color = onSurfaceColor, fontSize = 13.sp )
                Icon(
                    icon,
                    contentDescription = stringResource(id = R.string.star_icon_description)
                )
            }
            Text(text = subText, color = onSurfaceColor, fontSize = 13.sp)

        }
    }


}

@Composable
fun Overview(text: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Overview")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text)

    }
}

@Preview
@Composable
fun SpecItemOverview() {
    MaterialTheme {
        Surface {
            Overview(
                text = "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
            )
        }
    }
}

@Preview
@Composable
fun SpecItemPreview() {
    MaterialTheme {
        Surface {
            SpecItem(
                text = "8.2",
                subText = "10192 votes",
                icon = Icons.Filled.Star,
                direction = LayoutDirection.Ltr
            )
        }
    }
}
