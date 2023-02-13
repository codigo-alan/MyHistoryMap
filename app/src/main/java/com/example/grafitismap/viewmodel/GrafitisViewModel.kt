package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    val temporalMarkersList = List(12){Marker("marker","","",0,0)} //temporal markers list
    var markersLiveData = MutableLiveData<List<Marker>>().apply { value = listOf(Marker("marker1","Acontecimiento", "",0,0),Marker("marker2","","",0,0)) } //temporal markers list
    var selectedMarker = MutableLiveData<Marker>()

    fun selectMarker(newMarker: Marker){
        selectedMarker.postValue(newMarker)
    }
}