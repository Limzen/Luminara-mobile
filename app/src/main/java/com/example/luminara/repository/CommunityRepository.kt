package com.example.luminara.repository

import com.example.luminara.data.model.Community
import com.example.luminara.data.model.Trip
import com.example.luminara.data.remote.RetrofitInstance

class CommunityRepository {
    suspend fun getCommunities(): List<Community> {
        return RetrofitInstance.communityApi.getCommunities()
    }

    suspend fun getCommunitiesByReligion(religion: String): List<Community> {
        return RetrofitInstance.communityApi.getCommunitiesByReligion(religion)
    }

    suspend fun addCommunity(community: Community): Community {
        return RetrofitInstance.communityApi.addCommunity(community)
    }
}