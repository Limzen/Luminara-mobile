package com.example.luminara.data.remote.api

import com.example.luminara.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("register")
    suspend fun register(@Body user: User): User

    @POST("login")
    suspend fun login(@Body body: Map<String, String>): User
}