package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel
import com.example.grafitismap.models.modelToEntity

class GrafitisViewModel: ViewModel() {
    var markersModelLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    //Realm
    var realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    var markersEntityLiveData = markerRepository.markersListFlow().asLiveData()


    fun addMarkerEntity(newMarkerModel: MarkerModel){
        val newMarkerEntity = modelToEntity(newMarkerModel, realmRepo.user!!)
        markerRepository.addMarkerEntity(newMarkerEntity)
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersModelLiveData.postValue(markersModelLiveData.value?.plus(newMarkerModel))
    }


}