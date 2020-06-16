package com.example.app07.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {
    private val firestoreDB: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun getSavedCountry(): CollectionReference {
        var collectionReference = firestoreDB.collection("Category")
        return collectionReference
    }

    fun getSavedDetail(): CollectionReference {
        var collectionReference = firestoreDB.collection("Category")
        return collectionReference
    }
}