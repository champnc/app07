package com.example.app07

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app07.model.Detail
import com.example.app07.repository.FirestoreRepository


class DetailViewModel(): ViewModel() {
    var firebaseRepository = FirestoreRepository()
    var savedDetail : MutableLiveData<List<Detail>> = MutableLiveData()

    fun fetchNewDetail() {
        firebaseRepository.getSavedDetail().get().addOnSuccessListener { documents ->
            val listItemInfo : MutableList<Detail> = mutableListOf()
            for (document in documents) {
                listItemInfo.add(document.toObject(Detail::class.java))
            }
            savedDetail.value = listItemInfo
        }
    }
}