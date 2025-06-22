package com.example.luminara.ui.screens.trip

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Trip
import com.example.luminara.data.repository.TripRepository
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor (
    private val tripRepository: TripRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val trips: StateFlow<List<Trip>> = tripRepository.getTrips()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _uploading = MutableStateFlow(false)
    val uploading: StateFlow<Boolean> = _uploading

    fun addTrip(
        imageUri: Uri?,
        name: String,
        description: String,
        startDate: Timestamp,
        endDate: Timestamp,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = viewModelScope.launch {
        try {
            _uploading.value = true
           // val imageUrl = imageUri?.let { tripRepository.uploadImage(it) } ?: ""
            val trip = Trip(
                id = "",
                name = name,
                description = description,
                startDate = startDate,
                endDate = endDate,
                image = "https://images.pexels.com/photos/338416/pexels-photo-338416.jpeg"
            )
            tripRepository.addTrip(trip)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message ?: "Error")
        } finally {
            _uploading.value = false
        }
    }

    fun updateTrip(
        tripId: String,
        imageUri: Uri?,
        name: String,
        description: String,
        startDate: Timestamp,
        endDate: Timestamp,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = viewModelScope.launch {
        try {
          /*  _uploading.value = true

            val updatedImageUrl = if (imageUri != null) {
                tripRepository.uploadImage(imageUri)
            } else {
                originalTrip.image
            }
            */
            val updatedTrip = Trip(
                id = tripId,
                name = name,
                description = description,
                startDate = startDate,
                endDate = endDate,
               // image = updatedImageUrl
            )

            tripRepository.updateTrip(tripId, updatedTrip)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message ?: "Error")
        } finally {
            _uploading.value = false
        }
    }

    fun getTripById(tripId: String): StateFlow<Trip?> {
        val tripState = MutableStateFlow<Trip?>(null)
        viewModelScope.launch {
            tripState.value = tripRepository.getTripById(tripId)
        }
        return tripState
    }
}