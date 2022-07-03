package com.tmdb.domain.content.usecases

import com.aaa.clean.usecase.UseCase
import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.Carousel
import com.tmdb.domain.content.data.response.CarouselList
import com.tmdb.domain.content.data.response.ContentApiResponse
import com.tmdb.domain.content.repos.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContentFetcherUseCase @Inject internal constructor(
    private val contentRepository: ContentRepository
): UseCase(), ContentUseCase {
    //  TODO: Implement pagination.

    override fun fetchContent(contentRequest: ContentRequest): Flow<List<CarouselList>> {
        return contentRepository.fetchMovies(contentRequest).map { response ->
            transformCarousel(response)
        }
    }

    private fun transformCarousel(apiResponse: ContentApiResponse): List<CarouselList> {
        return apiResponse.contentItems.groupBy { contentItem ->
            contentItem.mediaType
        }.map { carousel ->
            CarouselList(
                carousel = Carousel(
                    name = carousel.key
                ),
                list = carousel.value
            )
        }.sortedBy { carouselList ->
            carouselList.carousel.name
        }
    }
}