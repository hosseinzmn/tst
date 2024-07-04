package com.example.borvvista.domain

import com.example.borvvista.domain.model.Song


interface SongRemoteDataSource {
    suspend fun getSongs(): List<Song>
}