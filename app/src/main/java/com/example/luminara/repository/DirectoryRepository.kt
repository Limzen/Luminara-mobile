package com.example.luminara.repository

import com.example.luminara.data.model.Directory
import com.example.luminara.data.remote.RetrofitInstance

class DirectoryRepository {
    suspend fun getDirectories(): List<Directory> {
        return RetrofitInstance.directoryApi.getDirectories()
    }

    suspend fun searchDirectories(query: String): List<Directory> {
        return RetrofitInstance.directoryApi.searchDirectories(query)
    }

    suspend fun getDirectoryById(id: Long): Directory {
        return RetrofitInstance.directoryApi.getDirectoryById(id)
    }
}
