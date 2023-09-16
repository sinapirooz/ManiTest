package com.example.manitest.util

class PathHandler {

    companion object {
        fun getImageUrlForPoster(path: String?): String {
            return if (path == null)
                ""
            else
                "https://image.tmdb.org/t/p/w500/$path"
        }

        fun getImageUrlForaBackdrop(path: String?): String {
            return if (path == null)
                ""
            else
                return "https://image.tmdb.org/t/p/w780/$path"
        }
    }


}