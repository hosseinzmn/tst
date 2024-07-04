package com.example.borvvista.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.borvvista.data.local.dao.SongDao

@Database(entities = [SongEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}