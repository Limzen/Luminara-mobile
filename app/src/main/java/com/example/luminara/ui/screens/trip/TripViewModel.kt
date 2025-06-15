package com.example.luminara.ui.screens.trip

import android.net.Uri
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
    private val tripRepository: TripRepository
): ViewModel() {
    val trips: StateFlow<List<Trip>> = tripRepository.getTrips()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _uploading = MutableStateFlow(false)
    val uploading: StateFlow<Boolean> = _uploading

    fun addTrip(
        imageUri: Uri?,
        name: String,
        description: String,
        startDate: Long,
        endDate: Long,
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
                startDate = Timestamp(startDate / 1000, ((startDate % 1000) * 1000000).toInt()),
                endDate = Timestamp(endDate / 1000, ((endDate % 1000) * 1000000).toInt()),
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
}