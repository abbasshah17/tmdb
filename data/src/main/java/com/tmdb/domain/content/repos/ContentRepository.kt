package com.tmdb.domain.content.repos

import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.ContentItem
import kotlinx.coroutines.flow.Flow

internal interface ContentRepository {

    fun fetchMovies(contentRequest: ContentRequest): Flow<List<ContentItem>>
}