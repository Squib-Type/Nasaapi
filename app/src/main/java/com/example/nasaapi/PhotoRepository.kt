package com.example.nasaapi

import com.example.nasaapi.api.AuthInterceptor
import com.example.nasaapi.api.ArtworkItem
import com.example.nasaapi.api.ArtInstituteAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository {
    private val artInstituteAPI: ArtInstituteAPI

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        artInstituteAPI = retrofit.create<ArtInstituteAPI>()
    }

    suspend fun fetchPhotos(count: Int = 50): List<ArtworkItem> {
        val response = artInstituteAPI.searchArtworks(
            query = "landscape",
            limit = count
        )

        return response.data.filter { it.imageId != null }
    }
}