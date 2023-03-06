package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grafitismap.database.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel: ViewModel() {

    private val realmRepo = ServiceLocator.realmRepo
    val nextFragment = MutableLiveData<SplashViewModelState>()

    fun start(){
        if(loggedIn()){
            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    realmRepo.remoteConfig()
                    nextFragment.postValue(SplashViewModelState.MAP)
                }
            }
        } else {
            nextFragment.postValue(SplashViewModelState.LOGIN)
        }
    }
    private fun loggedIn() = this.realmRepo.loggedIn()

}

enum class SplashViewModelState{
    LOGIN, MAP
}