package com.example.manitest.util

class PathHandler {

    companion object {
        fun getImageUrlForPoster(path: String): String {
            return "https://image.tmdb.org/t/p/w500/$path"
        }
    }


}