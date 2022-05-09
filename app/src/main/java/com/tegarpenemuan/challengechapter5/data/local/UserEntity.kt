package com.tegarpenemuan.challengechapter5.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "job") val job: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "token") val token: String? = null,
    @ColumnInfo(name = "image") val image: String? = null,
)

