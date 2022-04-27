package com.tegarpenemuan.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
//    @Query("SELECT * FROM user WHERE email=:email AND password=:password LIMIT 1")
//    fun getUser(email: String, password: String): UserEntity?

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long
}