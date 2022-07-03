package com.tmdb.domain.content.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentApiResponse(
    @Json(name = "page")
    val page: Long,

    @Json(name = "results")
    val contentItems: List<ContentItem>,

    @Json(name = "total_pages")
    val totalPages: Long,

    @Json(name = "total_results")
    val totalResults: Long
)
