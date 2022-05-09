package com.tegarpenemuan.myapplication.data.api.auth

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("device_name") var device_name: String? = null,
)