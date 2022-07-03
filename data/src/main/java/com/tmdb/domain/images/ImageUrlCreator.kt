package com.tmdb.domain.images

import android.content.Context
import com.tmdb.domain.R

fun Context.parseTmdbResourcePath(path: String?): String? {
    if (path == null) {
        return null
    }

    return getString(R.string.image_resource_base) + path
}