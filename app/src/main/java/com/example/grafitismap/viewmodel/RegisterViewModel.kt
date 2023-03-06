package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grafitismap.database.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    val realmRepo = ServiceLocator.realmRepo
    val userState = MutableLiveData<Boolean>()
    fun register(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.register(email, password)
                //TODO verify register and then login in fragments. Be careful with coroutines
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.login(email, password)
                userState.postValue(true)
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                //realmRepo.user?.logOut()
                realmRepo.realmApp.currentUser?.logOut()
                userState.postValue(false)
            }
        }
    }

}