package com.example.borvvista.data.local

import com.example.borvvista.data.local.dao.SongDao
import com.example.borvvista.domain.SongLocalDataSource
import com.example.borvvista.domain.model.Song
import com.example.borvvista.domain.model.SongMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SongLocalDataSourceImpl @Inject constructor(
    private val songDao: SongDao
) : SongLocalDataSource {

    override fun getSongs(): Flow<List<Song>> {
        return songDao.getAllSongs().map { entities ->
            entities.map { SongMapper.mapToDomain(it) }
        }
    }

    override suspend fun insertSongs(songs: List<Song>) {
        songDao.insertSongs(songs.map { SongMapper.mapToEntity(it) })
    }
}