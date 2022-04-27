package com.tegarpenemuan.challengechapter5.data.api.auth

import com.tegarpenemuan.myapplication.data.api.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthAPI {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("users/logout")
    suspend fun logout(@HeaderMap headers: Map<String, String>): Response<LogoutResponse>
}