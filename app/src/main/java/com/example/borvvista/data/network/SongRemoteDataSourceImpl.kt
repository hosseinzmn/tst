package com.example.borvvista.data.network

import com.example.borvvista.domain.model.Song
import com.example.borvvista.domain.SongRemoteDataSource
import javax.inject.Inject

class SongRemoteDataSourceImpl @Inject constructor(
    private val songApi: SongApi
) : SongRemoteDataSource {

    override suspend fun getSongs(): List<Song> {
        val response = songApi.getSongs()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            // Handle error (e.g., throw an exception)
            throw Exception("Failed to fetch songs")
        }
    }
}