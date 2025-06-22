package com.example.luminara.repository

import com.example.luminara.data.model.Directory
import com.example.luminara.data.remote.RetrofitInstance

class DirectoryRepository {
    suspend fun getDirectories(): List<Directory> {
        return RetrofitInstance.directoryApi.getDirectories()
    }
}
