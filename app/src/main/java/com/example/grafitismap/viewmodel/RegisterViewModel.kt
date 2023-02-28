package com.example.grafitismap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.grafitismap.database.ServiceLocator

class RegisterViewModel : ViewModel() {
    val realmRepo = ServiceLocator.realmRepo

    //register
    fun register(email: String, password: String){
        realmRepo.register(email, password)
    }
    //log in
    fun login(email: String, password: String){
        realmRepo.login(email, password)
    }
}