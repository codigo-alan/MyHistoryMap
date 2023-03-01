package com.example.grafitismap.database

import com.example.grafitismap.models.MarkerEntity
import com.example.grafitismap.models.MarkerModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

/**
 * To do all the operations like filters in db.
 */

class MarkerRepository(val realm: Realm) {

    fun markersListFlow() = realm.query<MarkerEntity>().find().asFlow().map { it.list.toList() }
    fun addMarkerEntity(newMarkerModel: MarkerModel, user: User){
        realm.writeBlocking {
            val markerEntity = MarkerEntity(
                name = newMarkerModel.name,
                category = null,//TODO how put Category type
                photo = newMarkerModel.photo,
                latitude = newMarkerModel.latitude.toString(),
                longitude = newMarkerModel.longitude.toString(),
                owner_id = user.id
            )
            copyToRealm(markerEntity)
        }
    }
}