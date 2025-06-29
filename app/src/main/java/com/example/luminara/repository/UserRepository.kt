package com.example.luminara.repository

import com.example.luminara.data.model.User
import com.example.luminara.data.remote.RetrofitInstance

class UserRepository {
    suspend fun register(user:User): User {
        return RetrofitInstance.userApi.register(user)
    }

    suspend fun login(email: String, password: String): User {
        return RetrofitInstance.userApi.login(mapOf("email" to email, "password" to password))
    }
}