package com.example.grafitismap.database

object ServiceLocator {
    var emailActive = "DEBUG" //TODO view with shared preferences
    val realmRepo = RealmRepo()
    lateinit var markerRepository: MarkerRepository
    /**
     * Call when user logged in and realm created
     */
    fun configureRealm(){
        requireNotNull(realmRepo.realm)
        val realm = realmRepo.realm!!
        markerRepository = MarkerRepository(realm)
    }

}