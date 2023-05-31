package com.example.orhan_ucar_odev8.room.dao

import androidx.room.*
import com.example.orhan_ucar_odev8.room.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE ad LIKE :name")
    suspend fun getUsersByName(name: String): List<User>

    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 10")
    suspend fun getLast10Users(): List<User>

    @Query("SELECT * FROM users WHERE grup = :grup")
    fun getUsersByGrup(grup: String): List<User>

    @Query("SELECT * FROM users WHERE ad = :ad AND soyad = :soyad")
    suspend fun getUserByAdSoyad(ad: String, soyad: String): User?


}
