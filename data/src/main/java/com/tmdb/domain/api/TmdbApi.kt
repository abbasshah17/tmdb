package com.tmdb.domain.api

import com.tmdb.domain.content.data.response.ContentApiResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface TmdbApi {
    @GET
    suspend fun getContent(@Url url: String, @QueryMap queryParams: Map<String, String>): ContentApiResponse
}
