package com.tmdb.domain.content.exceptions

class PageOutOfBoundsException(
    page: Int
): RuntimeException("TMDB API supports page lookup from 1 - 1000, there was an attempt to set the it to $page")