package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.MarkerModel

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersLiveData.postValue(markersLiveData.value?.plus(newMarkerModel))
    }
}