package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<Marker>>().apply { value = listOf() } //temporal markers list
    var selectedMarker = MutableLiveData<Marker>()

    fun selectMarker(newMarker: Marker){
        selectedMarker.postValue(newMarker)
    }
}