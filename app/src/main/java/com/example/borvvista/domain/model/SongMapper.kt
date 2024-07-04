package com.example.borvvista.domain.model

import com.example.borvvista.data.local.SongEntity

object SongMapper {
    fun mapToEntity(song: Song): SongEntity {
        return SongEntity(song.id, song.title, song.artist)
    }

    fun mapToDomain(entity: SongEntity): Song {
        return Song(entity.id, entity.title, entity.artist)
    }
}