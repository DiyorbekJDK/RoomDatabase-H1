package com.diyorbek.roomdatabase_h1.database
import androidx.room.*
@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFilm(userEntity: FilmEntity)

    @Query("SELECT * FROM FilmTable ORDER BY id DESC")
    fun getAllFilms(): List<FilmEntity>

    @Update
    fun updateFilm(filmEntity: FilmEntity)

    @Delete
    fun deleteFilm(filmEntity: FilmEntity)
}