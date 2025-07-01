package com.example.luminara.repository

import com.example.luminara.data.model.Itinerary
import com.example.luminara.data.remote.RetrofitInstance

class ItineraryRepository {
    suspend fun getItinerariesByTripId(tripId: Long): List<Itinerary> {
        return RetrofitInstance.itineraryApi.getItinerariesByTripId(tripId)
    }

    suspend fun createItinerary(itinerary: Itinerary): Itinerary {
        return RetrofitInstance.itineraryApi.createItinerary(itinerary)
    }

    suspend fun updateItinerary(id: Long, itinerary: Itinerary): Itinerary {
        return RetrofitInstance.itineraryApi.updateItinerary(id, itinerary)
    }

   /* suspend fun deleteItinerary(id: Long) {
        return RetrofitInstance.itineraryApi.deleteItinerary(id)
    }

    */
}