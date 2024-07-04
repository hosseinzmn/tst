package com.example.borvvista.domain

import com.example.borvvista.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongLocalDataSource {
    fun getSongs(): Flow<List<Song>>
    suspend fun insertSongs(songs: List<Song>)
}