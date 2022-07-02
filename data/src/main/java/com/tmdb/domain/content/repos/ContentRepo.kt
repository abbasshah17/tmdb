package com.tmdb.domain.content.repos

import com.aaa.clean.repo.Repository
import com.tmdb.domain.content.data.request.ContentRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepo @Inject internal constructor(
): Repository(), ContentRepository {

    override fun fetchMovies(contentRequest: ContentRequest): Flow<ContentApiResponse> = flow {
        TODO("Not yet implemented")
    }
}