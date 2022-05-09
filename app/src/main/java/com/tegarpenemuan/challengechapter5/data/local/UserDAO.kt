package com.tegarpenemuan.challengechapter5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tegarpenemuan.challengechapter5.data.local.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE email=:email LIMIT 1")
    fun getUsername(email: String): UserEntity?

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long
}