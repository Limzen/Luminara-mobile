package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Community
import com.example.luminara.data.model.Trip
import retrofit2.http.GET
import retrofit2.http.Query

interface CommunityApi {
    @GET("community")
    suspend fun getCommunities(): List<Community>

    @GET("community/religion")
    suspend fun getCommunitiesByReligion(@Query("religion") religion: String): List<Community>
}