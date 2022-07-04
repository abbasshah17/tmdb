package com.tmdb.domain.content.repos

import com.aaa.clean.repo.Repository
import com.aaa.network.InternetConnectivity
import com.tmdb.domain.api.TmdbApi
import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.response.ContentApiResponse
import com.tmdb.domain.di.SearchUrl
import com.tmdb.domain.exceptions.NotConnectedToInternetError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentRepo @Inject internal constructor(
    private val inetConnectivity: InternetConnectivity,
    @SearchUrl private val baseUrl: String,
    private val tmdbApi: TmdbApi
): Repository(), ContentRepository {

    override fun fetchMovies(contentRequest: ContentRequest): Flow<ContentApiResponse> = flow {
        if (!inetConnectivity.isConnected()) {
            throw NotConnectedToInternetError()
        }

        emit(tmdbApi.getContent(baseUrl, prepareQueryParams(contentRequest)))
    }.flowOn(Dispatchers.IO)

    private fun prepareQueryParams(contentRequest: ContentRequest): Map<String, String> {
        return mutableMapOf<String, String>().apply {
            put("page", contentRequest.page.toString())
            put("include_adult", contentRequest.adultContent.toString())

            put("query", contentRequest.query)
        }
    }
}