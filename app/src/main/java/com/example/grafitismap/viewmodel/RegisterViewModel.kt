package com.example.grafitismap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grafitismap.database.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    val realmRepo = ServiceLocator.realmRepo
    fun register(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.register(email, password)
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                realmRepo.login(email, password)
            }
        }
    }

}