package com.tmdb.domain.content.data.request

import com.tmdb.domain.content.exceptions.PageOutOfBoundsException

data class ContentRequest(
    val query: String,
    val adultContent: Boolean = false
) {
    var page: Int = 1
        set(value) {
            if (value < MIN_TMDB_API_PAGE || value > MAX_TMDB_API_PAGE) {
                throw PageOutOfBoundsException(value)
            }

            field = value
        }
}

const val MIN_TMDB_API_PAGE = 1
const val MAX_TMDB_API_PAGE = 1000