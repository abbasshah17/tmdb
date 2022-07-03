package com.tmdb.domain.content.data.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ContentItem(
    @Json(name = "id")
    val id: Long,

    @Json(name = "poster_path")
    val poster: String?,

    @Json(name = "backdrop_path")
    val backdrop: String?,

    @Json(name = "profile_path")
    val profile: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "overview")
    val description: String?,

    @Json(name = "media_type")
    val mediaType: String
): Parcelable {
    fun isVideoType(): Boolean {
        return (
                mediaType == "movie" ||
                mediaType == "tv"
        )
    }

    val contentTitle: String get() {
        return if (title.isNullOrBlank()) {
            name ?: ""
        } else {
            title
        }
    }

    val contentImage: String get() {
        return if (!poster.isNullOrBlank()) {
            poster
        } else if (!backdrop.isNullOrBlank()) {
            backdrop
        } else if (!profile.isNullOrBlank()) {
            profile
        } else {
            ""
        }
    }
}

val ContentItem.playUrl: String get() = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
