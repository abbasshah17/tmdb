package com.tmdb.domain.content.data.response

data class ContentApiResponse(
    val page: Long,
    val contentItems: List<ContentItem>,
    val totalPages: Long,
    val totalResults: Long
)
