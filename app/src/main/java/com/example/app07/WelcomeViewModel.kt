package com.example.app07

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app07.model.Category
import com.example.app07.repository.FirestoreRepository


class WelcomeViewModel(): ViewModel() {
    var firebaseRepository = FirestoreRepository()
    var savedCategory : MutableLiveData<List<Category>> = MutableLiveData()

    fun fetchNewCategory() {
        firebaseRepository.getSavedCountry().get().addOnSuccessListener { documents ->
            val listItemInfo : MutableList<Category> = mutableListOf()
            for (document in documents) {
                listItemInfo.add(document.toObject(Category::class.java))
            }
            savedCategory.value = listItemInfo
        }
    }
}