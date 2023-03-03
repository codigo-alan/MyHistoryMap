package com.example.grafitismap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grafitismap.database.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {

    val realmRepo = ServiceLocator.realmRepo

    fun logout(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.logout()
            }
        }
    }
}