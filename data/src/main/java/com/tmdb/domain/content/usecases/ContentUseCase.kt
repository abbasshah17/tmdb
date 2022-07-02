package com.tmdb.domain.content.usecases

import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.CarouselList
import kotlinx.coroutines.flow.Flow

internal interface ContentUseCase {
    fun fetchContent(contentRequest: ContentRequest): Flow<List<CarouselList>>
}