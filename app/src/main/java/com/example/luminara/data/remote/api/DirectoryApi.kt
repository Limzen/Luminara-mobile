package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Directory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DirectoryApi {
    @GET("directory")
    suspend fun getDirectories(): List<Directory>

    @GET("directory/search")
    suspend fun searchDirectories(@Query("q") query: String): List<Directory>

    @GET("directory/{id}")
    suspend fun getDirectoryById(@Path("id") id: Long): Directory
}