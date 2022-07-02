package com.tmdb.domain.di

import android.content.Context
import com.tmdb.domain.BuildConfig
import com.tmdb.domain.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @BaseUrl
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    @ApiVersion
    fun providesApiVersion(@ApplicationContext context: Context): String {
        return BuildConfig.API_VERSION
    }

    @Provides
    @Singleton
    @SearchUrl
    fun providesSearchUrl(@BaseUrl baseUrl: String, @ApiVersion apiVersion: String, @ApplicationContext context: Context): String {
        return baseUrl + apiVersion + context.getString(R.string.api_search_path)
    }

    @Provides
    @Singleton
    @TmdbApiKey
    fun providesTmdbApiKey(): String {
        return BuildConfig.API_KEY
    }
}