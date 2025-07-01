package com.example.luminara.ui.screens.review

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.Review
import com.example.luminara.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReviewViewModel: ViewModel() {
    private val repository = ReviewRepository()

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    fun getReviewsByDirectoryId(directoryId: Long) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewsByDirectoryId(directoryId)
                _reviews.value = result
            } catch (e: Exception) {
                Log.e("ReviewFetch", "Failed: ${e.message}", e)
            }
        }
    }

    fun submitReview(review: Review, directoryId: Long) {
        viewModelScope.launch {
            repository.createReview(review)
            getReviewsByDirectoryId(directoryId) // Refresh
        }
    }


}