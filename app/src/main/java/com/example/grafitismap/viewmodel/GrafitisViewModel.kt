package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.MarkerModel
import com.google.android.gms.maps.GoogleMap
import com.example.grafitismap.database.RealmRepo
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    var data = MutableLiveData<List<Marker>>().apply { value = listOf() } //temporal markers list of entity
    var selectedMarker = MutableLiveData<Marker>()
    val realmRepo = MutableLiveData<RealmRepo>()


    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersLiveData.postValue(markersLiveData.value?.plus(newMarkerModel))
    }


    fun registerUser(email: String, password: String){
        this.realmRepo.value?.register(email, password)
    }

    fun loginUser(email: String, password: String){
        val creds = this.realmRepo.value?.getCredentials(email, password)
        this.realmRepo.value?.login(creds!!) //TODO be carefull with null
    }
}