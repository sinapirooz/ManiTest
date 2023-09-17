package com.example.manitest.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.manitest.data.model.Genre
import com.example.manitest.data.model.MovieDetail
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun genreItemDisplaysCorrectly() {
        val genre = Genre(name = "Action")

        composeTestRule.setContent {
            GenreItem(item = genre)
        }

        composeTestRule
            .onNodeWithText("Action")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Action")
            .assertHeightIsAtLeast(10.dp)
            .assertWidthIsAtLeast(20.dp)
    }

    @Test
    fun specsRight() {

        composeTestRule.setContent {
            Box(
                modifier = Modifier.fillMaxSize().testTag("SpecsTestTag"),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Specs(movieDetail = movieDetail)
            }
        }

        composeTestRule.onNodeWithTag("SpecsTestTag").assertExists()

        composeTestRule.onNodeWithText("7.5").assertExists()
        composeTestRule.onNodeWithText("100 votes").assertExists()
        composeTestRule.onNodeWithText("en").assertExists()
        composeTestRule.onNodeWithText("Language").assertExists()
        composeTestRule.onNodeWithText("2023-09-30").assertExists()
        composeTestRule.onNodeWithText("Release Date").assertExists()
    }
}

val movieDetail = MovieDetail(
    title = "Test Movie",
    overview = "This is a test movie",
    genres = listOf(Genre("Action"),Genre("Crime")),
    posterPath = null,
    backdropPath = null,
    originalLanguage = "en",
    releaseD_date = "2023-09-30",
    voteAverage = 7.5f,
    voteCount = 100
)