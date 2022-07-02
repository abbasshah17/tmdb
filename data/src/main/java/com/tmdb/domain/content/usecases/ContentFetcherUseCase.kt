package com.tmdb.domain.content.usecases

import com.aaa.clean.usecase.UseCase
import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.CarouselList
import com.tmdb.domain.content.repos.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentFetcherUseCase @Inject internal constructor(
    private val contentRepository: ContentRepository
): UseCase(), ContentUseCase {
    override fun fetchContent(contentRequest: ContentRequest): Flow<List<CarouselList>> {
        TODO("Not yet implemented")
    }
}