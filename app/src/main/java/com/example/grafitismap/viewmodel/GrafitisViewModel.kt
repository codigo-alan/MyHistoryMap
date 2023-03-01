package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerEntity
import com.example.grafitismap.models.MarkerModel
import kotlinx.coroutines.flow.map

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    //Realm
    //val realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    //var markersListLiveData = MutableLiveData<List<MarkerEntity>>().apply { value = listOf() }

    init {

        //markerRepository = ServiceLocator.markerRepository
        //markersListLiveData = markerRepository.markersListFlow().map { it.list.toList() } as MutableLiveData<List<MarkerEntity>>
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersLiveData.postValue(markersLiveData.value?.plus(newMarkerModel))
    }


}