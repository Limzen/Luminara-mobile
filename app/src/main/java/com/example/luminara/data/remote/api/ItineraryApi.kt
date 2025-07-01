package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Itinerary
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ItineraryApi {
    @GET("itinerary/{tripId}")
    suspend fun getItinerariesByTripId(@Path("tripId") tripId: Long): List<Itinerary>

    @POST("itinerary")
    suspend fun createItinerary(@Body itinerary: Itinerary): Itinerary

    @PUT("itinerary/{id}")
    suspend fun updateItinerary(@Path("id") id: Long, @Body itinerary: Itinerary): Itinerary

   /* @DELETE("itinerary/{id}")
    suspend fun deleteItinerary(@Path("id") id: Long): Response<Unit>
    */
}