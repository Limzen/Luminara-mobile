package com.example.luminara.data.remote

import com.example.luminara.data.remote.api.CommunityApi
import com.example.luminara.data.remote.api.DirectoryApi
import com.example.luminara.data.remote.api.TripApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://localhost:3000/api/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val directoryApi: DirectoryApi by lazy { retrofit.create(DirectoryApi::class.java)}
    val tripApi: TripApi by lazy { retrofit.create(TripApi::class.java)}
    val communityApi: CommunityApi by lazy { retrofit.create(CommunityApi::class.java)}
}