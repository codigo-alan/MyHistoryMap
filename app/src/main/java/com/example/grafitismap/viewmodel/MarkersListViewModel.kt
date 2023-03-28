package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel

/**
 * Unused for the moment. It has a bug of empty list.
 */
class MarkersListViewModel : ViewModel() {

    var markersModelLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var markersModelTemp : List<MarkerModel> = listOf()
    var selectedMarkerModel = MutableLiveData<MarkerModel>()

    //Realm
    var realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    var markersEntityLiveData = markerRepository.markersByUser().asLiveData()

    fun entityToModel(){
        markersEntityLiveData.value?.forEach {
            markersModelTemp += it.toModel()
        }
        markersModelLiveData.postValue(markersModelTemp)
        markersModelTemp = listOf()
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }


}