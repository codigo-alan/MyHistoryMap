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
    //TODO make coroutines in viewModel, here only suspend
    var realmApp : App
    var user : User? = null
    var realm : Realm? = null
    lateinit var config : SyncConfiguration
    init {
        realmApp = createRealmApp()
    }

    private fun createRealmApp() = App.create(
            AppConfiguration.Builder("application-0-qderj") //app id from app services in atlas.
                .log(LogLevel.ALL)
                .build())

    private suspend fun remoteConfig() {
        config = SyncConfiguration.Builder(this.user!!, setOf(MarkerEntity::class, Category::class))
            .initialSubscriptions { realm ->
                add(
                    realm.query<MarkerEntity>(),
                    "All Markers"
                )
            }
            .waitForInitialRemoteData()
            .build()
        realm = Realm.open(this.config)
        realm!!.subscriptions.waitForSynchronization()

        ServiceLocator.configureRealm() //initialize the service locator entityRepository
    }

    private fun getCredentials(email: String, password: String) =
        Credentials.emailPassword(email, password)

    fun login(email: String, password: String){ //TODO verify
        val credentials = getCredentials(email, password)
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.login(credentials)
            user = realmApp.currentUser!!
            remoteConfig()
        }
    }

    fun register(email: String, password: String){ //TODO verify
        CoroutineScope(Dispatchers.IO).launch {
            realmApp.emailPasswordAuth.registerUser(email, password)
        }
    }

    fun loggedIn() = realmApp.currentUser?.loggedIn ?: false



}