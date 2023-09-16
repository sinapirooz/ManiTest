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
}