package com.example.luminara.ui.screens.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Community
import com.example.luminara.data.model.Trip
import com.example.luminara.repository.CommunityRepository
import com.example.luminara.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel: ViewModel() {
    private val repository = CommunityRepository()
    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    val communities: StateFlow<List<Community>> = _communities

    fun getCommunities() {
        viewModelScope.launch {
            try {
                val result = repository.getCommunities()
                _communities.value = result
                Log.d("CommunityViewModel", "Fetched communities: $result") // Log the result
            } catch (e: Exception) {
                Log.e("CommunityViewModel", "Error fetching communities: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getCommunitiesByReligion(religion: String) {
        viewModelScope.launch {
            try {
                val religionParam = if (religion == "All") "" else religion
                val result = repository.getCommunitiesByReligion(religionParam)
                _communities.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}