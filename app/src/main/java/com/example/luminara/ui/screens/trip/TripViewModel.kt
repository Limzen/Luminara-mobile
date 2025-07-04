package com.example.luminara.ui.screens.trip

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Trip
import com.example.luminara.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TripViewModel: ViewModel() {
    private val repository = TripRepository()
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips

    private val _selectedTrip = MutableStateFlow<Trip?>(null)
    val selectedTrip: StateFlow<Trip?> = _selectedTrip

    fun getTrips() {
        viewModelScope.launch {
            try {
                val result = repository.getTrips()
                _trips.value = result
                Log.d("TripsViewModel", "Fetched Trips: $result") // Log the result
            } catch (e: Exception) {
                Log.e("TripsViewModel", "Error fetching Trips: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getTripById(id: Long) {
        viewModelScope.launch {
            try {
                val trip = repository.getTripById(id)
                _selectedTrip.value = trip
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addTrip(trip: Trip, onSuccess: () -> Unit, onError: ()-> Unit) {
        viewModelScope.launch {
            try {
                repository.addTrip(trip)
                getTrips() // Refresh list
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("error", "$e")
                onError()
            }
        }
    }

    fun editTrip(trip: Trip, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.editTrip(trip)
                getTrips() // refresh
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun deleteTrip(id: Long, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteTrip(id)
                getTrips()
                onSuccess()
            } catch (e: Exception) {
                Log.e("TripsViewModel", "Error deleting trip: ${e.message}")
                onError()
            }
        }
    }
}