package com.example.grafitismap.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.grafitismap.database.MarkerRepository
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.models.MarkerModel
import io.realm.kotlin.types.ObjectId


class GrafitisViewModel: ViewModel() {
    var markersModelLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var markersModelTemp : List<MarkerModel> = listOf()
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel(
        name = "",
        category = "",
        photo = "",
        latitude = -1.0,
        longitude = -1.0)

    //Realm
    var realmRepo = ServiceLocator.realmRepo
    var markerRepository : MarkerRepository = ServiceLocator.markerRepository
    var markersEntityLiveData = markerRepository.markersListFlow().asLiveData()
    //var ownMarkersEntityLiveData = markerRepository.markersByUser().asLiveData()

    fun entityToModel(){
        markersEntityLiveData.value?.forEach {
            markersModelTemp += it.toModel()
        }
        markersModelLiveData.postValue(markersModelTemp)
        markersModelTemp = listOf()
    }

    fun addMarkerEntity(newMarkerModel: MarkerModel){
        val newMarker = newMarkerModel.toEntity(realmRepo.user!!)
        markerRepository.addMarkerEntity(newMarker)
        Log.d("new","${realmRepo.user!!.id}")
        Log.d("new","$newMarker")
    }

    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }

    fun deleteMarker(id: ObjectId){
        markerRepository.deleteMarkerEntity(id)
    }


}