package com.example.manitest.di

import com.example.manitest.data.repository.DetailRepository
import com.example.manitest.data.repository.MovieDetailRepositoryDefault
import com.example.manitest.service.AuthorizationInterceptor
import com.example.manitest.service.Endpoint
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Qualifier
    annotation class DefaultDetailRepository

    @Singleton
    @Provides
    fun provideHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder().serializeNulls().create()
        )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoint.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit): Endpoint =
        retrofit.create(Endpoint::class.java)

    @Singleton
    @AppModule.DefaultDetailRepository
    @Provides
    fun bindDetailRepository(impl: MovieDetailRepositoryDefault): DetailRepository {
        return impl
    }
}

