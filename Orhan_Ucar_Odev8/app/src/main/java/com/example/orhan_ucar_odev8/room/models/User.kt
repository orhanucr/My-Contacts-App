package com.example.orhan_ucar_odev8.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    val ad: String,
    val soyad: String,
    val telefon: String,
    val adres: String,
    val grup: String
): Serializable