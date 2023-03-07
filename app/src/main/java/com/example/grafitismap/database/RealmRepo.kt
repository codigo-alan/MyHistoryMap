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
    var user : User? = null
    var realm : Realm? = null
    lateinit var config : SyncConfiguration
    init {
        realmApp = createRealmApp()
        user = realmApp.currentUser
    }

    private fun createRealmApp() = App.create(
            AppConfiguration.Builder("application-0-qderj") //app id from app services in atlas.
                .log(LogLevel.ALL)
                .build())

    suspend fun remoteConfig() {
        config = SyncConfiguration.Builder(this.user!!, setOf(MarkerEntity::class, Category::class))
            .initialSubscriptions { realm ->
                add(
                    realm.query<MarkerEntity>(),
                    "All Markers"
                )
                add(
                    realm.query<Category>(),
                    "All Category"
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

    suspend fun login(email: String, password: String){

        val credentials = getCredentials(email, password)
        realmApp.login(credentials)
        user = realmApp.currentUser!!
        remoteConfig()

    }

    suspend fun register(email: String, password: String){

        realmApp.emailPasswordAuth.registerUser(email, password)

    }

    fun loggedIn() = realmApp.currentUser?.loggedIn ?: false



}