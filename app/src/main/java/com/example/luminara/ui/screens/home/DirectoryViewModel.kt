package com.example.luminara.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Directory
import com.example.luminara.repository.DirectoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DirectoryViewModel : ViewModel() {
    private val repository = DirectoryRepository()

    private val _directories = MutableStateFlow<List<Directory>>(emptyList())
    val directories: StateFlow<List<Directory>> = _directories

    private val _searchResults = MutableStateFlow<List<Directory>>(emptyList())
    val searchResults: StateFlow<List<Directory>> = _searchResults

    private val _selectedDirectory = MutableStateFlow<Directory?>(null)
    val selectedDirectory: StateFlow<Directory?> = _selectedDirectory

    fun fetchDirectories() {
        viewModelScope.launch {
            try {
                val result = repository.getDirectories()
                _directories.value = result
                Log.d("DirectoryViewModel", "Fetched directories: $result") // Log the result
            } catch (e: Exception) {
                Log.e("DirectoryViewModel", "Error fetching directories: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun searchDirectories(query: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchDirectories(query)
                _searchResults.value = result
            } catch (e: Exception) {
                Log.e("Search", "Error: ${e.message}")
            }
        }
    }


    fun getDirectoryById(id: Long) {
        viewModelScope.launch {
            try {
                val result = repository.getDirectoryById(id)
                _selectedDirectory.value = result
            } catch (e: Exception) {
                Log.e("DirectoryViewModel", "Error fetching directory: ${e.message}")
            }
        }
    }

}