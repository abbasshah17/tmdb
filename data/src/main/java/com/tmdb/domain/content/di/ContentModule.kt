package com.tmdb.domain.content.di

import com.tmdb.domain.content.repos.ContentRepo
import com.tmdb.domain.content.repos.ContentRepository
import com.tmdb.domain.content.usecases.ContentFetcherUseCase
import com.tmdb.domain.content.usecases.ContentUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ContentModule {
    @Binds
    internal abstract fun bindsContentUseCase(contentFetcher: ContentFetcherUseCase): ContentUseCase

    @Binds
    internal abstract fun bindsContentRepository(contentRepository: ContentRepo): ContentRepository
}