package com.example.grafitismap.database

import com.example.grafitismap.models.MarkerEntity
import com.example.grafitismap.models.MarkerModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * To do all the operations like filters in db.
 */

class MarkerRepository(val realm: Realm) {

    fun markersListFlow() : Flow<List<MarkerEntity>> = realm.query<MarkerEntity>().find().asFlow().map { it.list.toList() }

    fun markersByUser() : Flow<List<MarkerEntity>> = realm.query<MarkerEntity>("owner_id = 'Michigan J. Frog'").find().asFlow().map { it.list.toList() }

    fun addMarkerEntity(markerEntity: MarkerEntity){

        realm.writeBlocking {

            copyToRealm(markerEntity)

        }

    }
}