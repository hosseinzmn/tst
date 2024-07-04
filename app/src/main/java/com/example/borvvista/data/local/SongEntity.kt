package com.example.borvvista.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs_table")
data class SongEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val artist: String
)