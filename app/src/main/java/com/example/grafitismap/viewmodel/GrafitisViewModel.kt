package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.database.RealmRepo
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    var data = MutableLiveData<List<Marker>>().apply { value = listOf() } //temporal markers list
    var selectedMarker = MutableLiveData<Marker>()
    val realmRepo = MutableLiveData<RealmRepo>()

    init {
        realmRepo.value?.createRealmApp()
    }

    fun selectMarker(newMarker: Marker){
        selectedMarker.postValue(newMarker)
    }
}