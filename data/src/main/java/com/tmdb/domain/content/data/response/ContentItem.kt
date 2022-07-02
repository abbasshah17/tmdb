package com.tmdb.domain.content.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentItem(
    @Json(name = "poster_path")
    val poster: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "overview")
    val description: String?,

    @Json(name = "media_type")
    val mediaType: String,
)
