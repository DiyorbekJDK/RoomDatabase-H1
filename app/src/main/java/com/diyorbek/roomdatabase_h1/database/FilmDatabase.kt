package com.diyorbek.roomdatabase_h1.database

import android.content.Context
import androidx.room.*

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract val dao: FilmDao

    companion object {
        @Volatile
        private var instance: FilmDatabase? = null

        operator fun invoke(context: Context): FilmDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun createDatabase(context: Context): FilmDatabase {
            return Room.databaseBuilder(
                context,
                FilmDatabase::class.java,
                "Film.db"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}