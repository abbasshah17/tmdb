package com.tmdb.domain.content.usecases

import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.Carousel
import com.tmdb.domain.content.data.response.ContentItem
import kotlinx.coroutines.flow.Flow

internal interface ContentUseCase {
    fun fetchContent(contentRequest: ContentRequest): Flow<Pair<List<Carousel>, List<ContentItem>>>
}