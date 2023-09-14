package com.example.manitest.service

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthorizationInterceptor@Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYmI1NGRjNDg2NDE1ZTBkYzk1OGVlZTk0MGEwNzllOCIsInN1YiI6IjY1MDE2YWI5ZGI0ZWQ2MTAzODU2NTBhMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lodyIaFGEFfZiCPeSlj7syn8RzDRLKutkRL7bWY6FUQ")
                .build()
        )
    }
}