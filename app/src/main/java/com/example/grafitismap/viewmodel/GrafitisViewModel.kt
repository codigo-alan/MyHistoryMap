package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.MarkerModel

class GrafitisViewModel: ViewModel() {
    var markersLiveData = MutableLiveData<List<MarkerModel>>().apply { value = listOf() }
    var selectedMarkerModel = MutableLiveData<MarkerModel>()
    var newMarkerTemp = MarkerModel("","","",-1.0,-1.0)

    //temporal for realm
    /*var data = MutableLiveData<List<MarkerEntity>>().apply { value = listOf() } //temporal markers list of entity
    val realmRepo = ServiceLocator.realmRepo //contains all data from realm*/


    fun selectMarker(newMarkerModel: MarkerModel){
        selectedMarkerModel.postValue(newMarkerModel)
    }
    fun addMarker(newMarkerModel: MarkerModel){
        markersLiveData.postValue(markersLiveData.value?.plus(newMarkerModel))
    }


/*    fun registerUser(email: String, password: String){
        this.realmRepo.register(email, password)
    }*/
    /*fun loginUser(email: String, password: String){
        val creds = this.realmRepo.value?.getCredentials(email, password)
        this.realmRepo.login(creds!!) //TODO be carefull with null
    }*/
    //fun verifyLogged() = this.realmRepo.loggedIn() ?: false

}