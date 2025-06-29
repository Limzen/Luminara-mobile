package com.example.luminara.ui.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.datastore.UserDataStore
import com.example.luminara.data.model.User
import com.example.luminara.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel(
    private val dataStore: UserDataStore
) : ViewModel() {
    private val repository: UserRepository = UserRepository()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser


    fun register(user: User, onSuccess: () -> Unit, onError: ()-> Unit) = viewModelScope.launch {
        try {
            val newUser = repository.register(user)
            dataStore.saveUser(newUser)
            _currentUser.value = newUser
            onSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            onError()
        }
    }

    fun login(email: String, password: String,onSuccess: () -> Unit, onError: ()-> Unit) = viewModelScope.launch {
        try {
            val user = repository.login(email, password)
            dataStore.saveUser(user)
            _currentUser.value = user
            onSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            onError()
        }
    }

}