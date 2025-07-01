package com.example.luminara.ui.screens.community

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Community
import com.example.luminara.data.model.Trip
import com.example.luminara.repository.CommunityRepository
import com.example.luminara.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CommunityViewModel: ViewModel() {
    private val repository = CommunityRepository()
    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    val communities: StateFlow<List<Community>> = _communities

    var selectedCommunity by mutableStateOf<Community?>(null)
        private set

    fun getCommunities() {
        viewModelScope.launch {
            try {
                val result = repository.getCommunities()
                Log.d("result","$result")
                _communities.value = result
            } catch (e: Exception) {
                Log.e("CommunityViewModel", "Error fetching communities: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun selectCommunity(community: Community) {
        selectedCommunity = community
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

    fun addCommunity(community: Community, onSuccess : () -> Unit, onError : ()-> Unit) {
        viewModelScope.launch {
            try {
                repository.addCommunity(community)
                getCommunities()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("error", "$e")
                onError()
            }
        }
    }

}