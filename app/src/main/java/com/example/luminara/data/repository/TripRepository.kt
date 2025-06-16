package com.example.luminara.data.repository

import android.net.Uri
import android.util.Log
import com.example.luminara.data.model.Trip
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class TripRepository @Inject constructor(
    private val firestore: FirebaseFirestore
){
    private val tripCollection = firestore.collection("trips")

    fun getTrips(): Flow<List<Trip>> = callbackFlow {
        val listener = tripCollection.addSnapshotListener { snapshot, _ ->
            val trips = snapshot?.documents?.mapNotNull { document ->
                val trip = document.toObject(Trip::class.java)
                Log.d("TripRepository", "Loaded trip: $trip")
                trip?.copy(id = document.id)
            } ?: emptyList()
            trySend(trips)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addTrip(trip: Trip) {
        tripCollection.add(trip)
    }

    suspend fun updateTrip(tripId:String, trip: Trip) {
        tripCollection.document(tripId).set(trip)
    }

    suspend fun getTripById(tripId: String): Trip? {
        return try {
            val doc = Firebase.firestore.collection("trips").document(tripId).get().await()
            doc.toObject(Trip::class.java)?.copy(id = doc.id)
        } catch (e: Exception) {
            null
        }
    }

    /*suspend fun uploadImage(imageUri: Uri): String {
        val fileName = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance().reference.child("trip_images/$fileName")
        storageRef.putFile(imageUri).await()
        return storageRef.downloadUrl.await().toString()
    }
    */
}