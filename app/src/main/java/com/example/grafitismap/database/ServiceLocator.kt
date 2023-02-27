package com.example.grafitismap.database

import com.example.grafitismap.database.RealmRepo

object ServiceLocator {
    val realmRepo = RealmRepo()
    lateinit var entityRepository: EntityRepository
    /**
     * Call when user logged in and realm created
     */
    fun configureRealm(){
        requireNotNull(realmRepo.realm)
        val realm = realmRepo.realm!!
        entityRepository = EntityRepository(realm)
    }
}