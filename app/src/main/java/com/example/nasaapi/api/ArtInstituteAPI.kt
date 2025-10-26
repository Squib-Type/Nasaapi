package com.example.nasaapi.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ArtInstituteAPI {
    @GET("artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String = "landscape",
        @Query("limit") limit: Int = 50,
        @Query("fields") fields: String = "id,title,artist_display,date_display,medium_display,dimensions,image_id,thumbnail"
    ): ArtworkResponse
}