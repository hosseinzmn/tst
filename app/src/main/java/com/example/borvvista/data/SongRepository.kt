package com.example.borvvista.data


import com.example.borvvista.domain.SongLocalDataSource
import com.example.borvvista.domain.SongRemoteDataSource
import com.example.borvvista.domain.model.Song
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val remoteDataSource: SongRemoteDataSource,
    private val localDataSource: SongLocalDataSource
) {

    suspend fun fetchAndSaveSongs() {
        try {
            val songs = remoteDataSource.getSongs()
            localDataSource.insertSongs(songs)
        } catch (e: Exception) {

            throw e
        }
    }

    fun getSongsFromDatabase(): Flow<List<Song>> {
        return localDataSource.getSongs()
    }
}