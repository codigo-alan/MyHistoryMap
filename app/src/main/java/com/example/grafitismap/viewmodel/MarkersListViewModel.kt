package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel

class MarkersListViewModel : ViewModel() {

    //Realm
    var realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    var markersEntityLiveData = markerRepository.markersListFlow().asLiveData()

    var markersModelLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()

    fun entityToModel() {
        markersEntityLiveData.value?.forEach {
            markersModelLiveData.postValue(markersModelLiveData.value?.plus(it.toModel()))
        }
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
}