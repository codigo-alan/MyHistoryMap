package com.example.grafitismap.database

import com.example.grafitismap.models.MarkerEntity
import com.example.grafitismap.models.MarkerModel
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.User

/**
 * To do all the operations like filters in db.
 */

class EntityRepository(val realm: Realm) {

    //val realm = realm
    fun addMarkerEntity(newMarkerModel: MarkerModel, user: User){
        realm.writeBlocking {
            val markerEntity = MarkerEntity(
                name = newMarkerModel.name,
                //category = TODO how put Category type
                photo = newMarkerModel.photo,
                latitude = newMarkerModel.latitude.toString(),//TODO error with Long, need String
                longitude = newMarkerModel.longitude.toString(),
                owner_id = user.id
            )
            copyToRealm(markerEntity)
        }
    }
}