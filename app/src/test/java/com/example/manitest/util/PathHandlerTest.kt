package com.example.manitest.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class PathHandlerTest {

    @Test
    fun getImageUrlForPoster_null_returnsEmptyStr() {
        val path = null

        assertThat(PathHandler.getImageUrlForPoster(path), Matchers.`is`(""))
    }

    @Test
    fun getImageUrlForPoster_Str_returnsCorrect() {
        val path = "iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg"
        val final = "https://image.tmdb.org/t/p/w500/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg"
        assertThat(PathHandler.getImageUrlForPoster(path), Matchers.`is`(final))
    }
}