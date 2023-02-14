package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.database.RealmRepo
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    var data = MutableLiveData<List<Marker>>().apply { value = listOf() } //temporal markers list
    var selectedMarker = MutableLiveData<Marker>()
    val realmRepo = MutableLiveData<RealmRepo>()


    fun selectMarker(newMarker: Marker){
        selectedMarker.postValue(newMarker)
    }

    fun registerUser(email: String, password: String){
        this.realmRepo.value?.register(email, password)
    }

    fun loginUser(email: String, password: String){
        val creds = this.realmRepo.value?.getCredentials(email, password)
        this.realmRepo.value?.login(creds!!) //TODO be carefull with null
    }
}