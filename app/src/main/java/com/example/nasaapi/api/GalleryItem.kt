package com.example.nasaapi.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtworkResponse(
    @Json(name = "data") val data: List<ArtworkItem>
)

@JsonClass(generateAdapter = true)
data class ArtworkItem(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "artist_display") val artistDisplay: String? = null,
    @Json(name = "date_display") val dateDisplay: String? = null,
    @Json(name = "medium_display") val mediumDisplay: String? = null,
    @Json(name = "dimensions") val dimensions: String? = null,
    @Json(name = "image_id") val imageId: String? = null,
    @Json(name = "thumbnail") val thumbnail: Thumbnail? = null,
) {
    val imageUrl: String
        get() = if (imageId != null) {
            "https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg"
        } else {
            thumbnail?.lqip ?: ""
        }
    val largeImageUrl: String
        get() = if (imageId != null) {
            "https://www.artic.edu/iiif/2/$imageId/full/1686,/0/default.jpg"
        } else {
            imageUrl
        }
// https://api.artic.edu/docs/#iiif-image-api
    val detailText: String
        get() = buildString {
            append(title)
            artistDisplay?.let { append("\n\n$it") }
            dateDisplay?.let { append("\n$it") }
            mediumDisplay?.let { append("\n\n$it") }
            dimensions?.let { append("\n$it") }
        }
}

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "lqip") val lqip: String? = null,
    @Json(name = "width") val width: Int? = null,
    @Json(name = "height") val height: Int? = null,
    @Json(name = "alt_text") val altText: String? = null
)