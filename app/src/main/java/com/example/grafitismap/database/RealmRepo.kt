package com.example.grafitismap.database

import com.example.grafitismap.models.Marker
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RealmRepo {
    var realmApp : App
    lateinit var user : User
    lateinit var config : SyncConfiguration
    //lateinit var creds : Credentials

    init {
        realmApp = createRealmApp()
        //user = realmApp.currentUser!!
        //config = remoteConfig()
    }

    private fun createRealmApp() = App.create(
            AppConfiguration.Builder("application-0-qderj") //app id from app services in atlas.
                .log(LogLevel.ALL)
                .build())

    private fun remoteConfig() = SyncConfiguration.Builder(this.user, setOf(Marker::class))
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


    fun getCredentials(email: String, password: String) =
        Credentials.emailPassword(email, password)

    fun login(credentials: Credentials){ //TODO verify
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.login(credentials)
            user = realmApp.currentUser!!
            config = remoteConfig()
        }
    }

    fun register(email: String, password: String){ //TODO verify
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.emailPasswordAuth.registerUser(email, password)
            withContext(Dispatchers.Main) {

            }
        }

    }




}