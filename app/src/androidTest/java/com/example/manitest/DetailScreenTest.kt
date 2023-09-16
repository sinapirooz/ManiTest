package com.example.manitest

import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.manitest.data.model.Genre
import com.example.manitest.ui.GenreItem
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
}