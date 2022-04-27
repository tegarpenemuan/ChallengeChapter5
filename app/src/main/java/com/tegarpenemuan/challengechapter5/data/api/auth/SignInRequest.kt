package com.tegarpenemuan.myapplication.data.api.auth

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("login") var login: String? = null,
    @SerializedName("password") var password: String? = null
)