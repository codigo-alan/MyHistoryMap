package com.example.grafitismap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grafitismap.database.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel: ViewModel() {

    val realmRepo = ServiceLocator.realmRepo

    fun loggedIn() = this.realmRepo.loggedIn()
    fun remoteConfig() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                //val user = realmRepo.user
                realmRepo.remoteConfig()
            }
        }
    }


}