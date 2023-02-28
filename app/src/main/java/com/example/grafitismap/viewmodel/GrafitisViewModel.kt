package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.database.EntityRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    //Realm
    val realmRepo = ServiceLocator.realmRepo
    var entityRepository : EntityRepository

    init {
        ServiceLocator.configureRealm() //initialize the service locator entityRepository
        entityRepository = ServiceLocator.entityRepository
    }

    fun openConnections(){
        realmRepo.openConnections()
    }
    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersLiveData.postValue(markersLiveData.value?.plus(newMarkerModel))
    }



}