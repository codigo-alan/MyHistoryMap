package com.example.grafitismap.database

import com.example.grafitismap.models.Category
import com.example.grafitismap.models.MarkerEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.*
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.*

class RealmRepo {
    var realmApp : App
    lateinit var user : User
    lateinit var config : SyncConfiguration
    //lateinit var creds : Credentials
    var realm : Realm? = null
    init {
        realmApp = createRealmApp()
        //user = realmApp.currentUser!!
        //config = remoteConfig()
    }

    private fun createRealmApp() = App.create(
            AppConfiguration.Builder("application-0-qderj") //app id from app services in atlas.
                .log(LogLevel.ALL)
                .build())

    private fun remoteConfig() = SyncConfiguration.Builder(this.user, setOf(MarkerEntity::class, Category::class))
        .initialSubscriptions { realm ->
            add(
                realm.query<MarkerEntity>(),
                "All Markers"
            )
        }
        .waitForInitialRemoteData()
        .build()

    fun openConnections(){
        realm = Realm.open(this.config)
        runBlocking {
            realm?.subscriptions?.waitForSynchronization()
        }
    }

    private fun getCredentials(email: String, password: String) =
        Credentials.emailPassword(email, password)

    fun login(email: String, password: String){ //TODO verify
        val credentials = getCredentials(email, password)
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.login(credentials)
            user = realmApp.currentUser!!
            config = remoteConfig()
        }
    }

    fun register(email: String, password: String){ //TODO verify
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.emailPasswordAuth.registerUser(email, password)
        }
    }

    fun loggedIn() = realmApp.currentUser?.loggedIn ?: false



}