package com.example.luminara.ui.screens.itinerary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Itinerary
import com.example.luminara.repository.ItineraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItineraryViewModel : ViewModel() {
    private val repository = ItineraryRepository()

    private val _itineraries = MutableStateFlow<List<Itinerary>>(emptyList())
    val itineraries: StateFlow<List<Itinerary>> = _itineraries

    fun getItineraries(tripId: Long) {
        viewModelScope.launch {
            _itineraries.value = repository.getItinerariesByTripId(tripId)
        }
    }

    fun createItinerary(itinerary: Itinerary, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                repository.createItinerary(itinerary)
                onSuccess()
            } catch (e: Exception) {
                Log.e("Itinerary ViewModel", "Create failed: ${e.message}")
                onError(e)
            }
        }
    }

    fun updateItinerary(id: Long, itinerary: Itinerary, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.updateItinerary(id, itinerary)
            onSuccess()
        }
    }

   /* fun deleteItinerary(id: Long, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.deleteItinerary(id)
            onSuccess()
        }
    }

    */
}