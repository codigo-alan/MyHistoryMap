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
                try {
                    realmRepo.register(email, password)
                    realmRepo.login(email, password)
                    userState.postValue(true)
                } catch (e: Exception) {
                    userState.postValue(false)
                }
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    realmRepo.login(email, password)
                    userState.postValue(true)
                    ServiceLocator.emailActive = email
                } catch (e: Exception) {
                    userState.postValue(false)
                }
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.user?.logOut()
                userState.postValue(false)
            }
        }
    }

}