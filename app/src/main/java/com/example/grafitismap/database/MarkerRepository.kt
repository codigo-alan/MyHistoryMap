package com.example.grafitismap.database

import com.example.grafitismap.models.MarkerEntity
import com.example.grafitismap.models.MarkerModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.types.ObjectId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * To do all the operations like filters in db.
 */

class MarkerRepository(val realm: Realm, val user: User) {

    fun markersListFlow() : Flow<List<MarkerEntity>> = realm.query<MarkerEntity>().find().asFlow().map { it.list.toList() }

    //TODO not works with owner_id, but yes with name for example.
    fun markersByUser() : Flow<List<MarkerEntity>> = realm.query<MarkerEntity>("owner_id == $0", user.id).find().asFlow().map { it.list.toList() }

    fun addMarkerEntity(markerEntity: MarkerEntity){

        realm.writeBlocking {

            copyToRealm(markerEntity)

        }

    }

    fun deleteMarkerEntity(id: ObjectId){
        realm.writeBlocking {
            val markerEntity : MarkerEntity =
                this.query<MarkerEntity>("_id == $0", id).find().first()

            delete(markerEntity)
        }
    }

}