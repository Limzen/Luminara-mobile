package com.example.luminara.repository

import com.example.luminara.data.model.Trip
import com.example.luminara.data.remote.RetrofitInstance

class TripRepository {
    suspend fun getTrips(): List<Trip> {
        return RetrofitInstance.tripApi.getTrips()
    }

    suspend fun getTripById(id: Long): Trip {
        return RetrofitInstance.tripApi.getTripById(id)
    }

    suspend fun addTrip(trip: Trip): Trip {
        return RetrofitInstance.tripApi.addTrip(trip)
    }

    suspend fun editTrip(trip: Trip): Trip {
        return RetrofitInstance.tripApi.editTrip(trip.id, trip)
    }

}