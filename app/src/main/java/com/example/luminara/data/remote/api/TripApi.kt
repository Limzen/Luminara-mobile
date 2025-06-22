package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Trip
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TripApi {
    @GET("trip")
    suspend fun getTrips(): List<Trip>

    @GET("trip/{id}")
    suspend fun getTripById(@Path("id") id: Long): Trip

    @POST("addTrip")
    suspend fun addTrip(@Body trip: Trip): Trip

    @PUT("trip/{id}")
    suspend fun editTrip(@Path("id") id: Long, @Body trip: Trip): Trip
}