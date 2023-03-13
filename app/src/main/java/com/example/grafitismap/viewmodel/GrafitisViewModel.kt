package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel


class GrafitisViewModel: ViewModel() {
    var markersModelLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var markersModelTemp : List<MarkerModel> = listOf()
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    //Realm
    var realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    var markersEntityLiveData = markerRepository.markersListFlow().asLiveData()

    fun entityToModel(){
        markersEntityLiveData.value?.forEach {
            markersModelTemp += it.toModel()
        }
        markersModelLiveData.postValue(markersModelTemp)
        markersModelTemp = listOf()
    }

    fun addMarkerEntity(newMarkerModel: MarkerModel){
        markerRepository.addMarkerEntity(newMarkerModel.toEntity(realmRepo.user!!))
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }


}