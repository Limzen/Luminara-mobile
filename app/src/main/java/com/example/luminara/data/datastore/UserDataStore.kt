package com.example.luminara.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.luminara.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore (private val context: Context) {
    private val Context.dataStore by preferencesDataStore("user_prefs")
    private val USER_ID = longPreferencesKey("user_id")
    private val USERNAME = stringPreferencesKey("username")

    suspend fun saveUser(user: User) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = user.id
            prefs[USERNAME] = user.username
        }
    }

    suspend fun getUser(): User? {
        val prefs = context.dataStore.data.first()
        val id = prefs[USER_ID] ?: return null
        val username = prefs[USERNAME] ?: return null
        return User(id, "", username, "", "")
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
