package com.example.borvvista.data.network

import com.example.borvvista.domain.model.Song
import retrofit2.http.GET
import retrofit2.Response

interface SongApi {
    @GET("songs") // Replace with the actual endpoint when known
    suspend fun getSongs(): Response<List<Song>>
}