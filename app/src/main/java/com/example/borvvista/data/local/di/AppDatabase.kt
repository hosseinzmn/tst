package com.example.borvvista.data.local.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.borvvista.data.local.SongEntity
import com.example.borvvista.data.local.SongLocalDataSourceImpl
import com.example.borvvista.data.local.dao.SongDao
import com.example.borvvista.domain.SongLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [SongEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "song_database"
        ).build()
    }

    @Provides
    fun provideSongDao(appDatabase: AppDatabase): SongDao {
        return appDatabase.songDao()
    }

    @Provides
    @Singleton
    fun provideSongLocalDataSource(songDao: SongDao): SongLocalDataSource {
        return SongLocalDataSourceImpl(songDao)
    }
}