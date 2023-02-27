package com.example.grafitismap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.grafitismap.database.ServiceLocator

class SplashViewModel: ViewModel() {

    val realmRepo = ServiceLocator.realmRepo

    fun loggedIn() = this.realmRepo.loggedIn()


}