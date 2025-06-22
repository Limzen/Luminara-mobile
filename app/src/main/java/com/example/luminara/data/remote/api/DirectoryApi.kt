package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Directory
import retrofit2.http.GET

interface DirectoryApi {
    @GET("directories")
    suspend fun getDirectories(): List<Directory>
}