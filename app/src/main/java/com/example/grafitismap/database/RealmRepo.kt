package com.example.grafitismap.database

import com.example.grafitismap.models.Marker
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration

class RealmRepo {
    lateinit var realmApp : App
    lateinit var creds : Credentials
    lateinit var config : SyncConfiguration
    val user = realmApp.currentUser!!

    fun createRealmApp(){
        realmApp = App.create(
            AppConfiguration.Builder("application-0-qderj") //app id from app services in atlas.
                .log(LogLevel.ALL)
                .build())
    }

    fun getCredentials(email: String, password: String){
        creds = Credentials.emailPassword(email, password)
    }

    //realmApp.login(creds)//need login to register successfully. THis line is principally to log in the user.
    //realmApp.emailPasswordAuth.registerUser(userName, userPassword) //register user.

    suspend fun login(){
        this.realmApp.login(this.creds)
    }

    suspend fun register(email: String, password: String){
        this.realmApp.emailPasswordAuth.registerUser(email, password)
    }

    fun remoteConfig(){ //TODO change data
        config = SyncConfiguration.Builder(user, setOf(Marker::class))
            .initialSubscriptions { realm ->
                add(
                    realm.query<Marker>(),
                    "All Markers"
                )
                add(
                    realm.query<Marker>("owner_id == $0", realmApp.currentUser!!.id),
                    "User's Markers"
                )
            }
            .waitForInitialRemoteData()
            .build()
    }

}