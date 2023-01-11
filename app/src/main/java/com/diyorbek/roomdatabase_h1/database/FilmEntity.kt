package com.diyorbek.roomdatabase_h1.database

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "FilmTable")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val filmName: String,
    val filmAuthors: String,
    val filmDesc: String,
    val filmData: String
) : Parcelable
