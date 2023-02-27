package com.example.grafitismap.models

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Marker (
    @PrimaryKey
    var _id: ObjectId = ObjectId.create() ,
    var name: String = "" ,
    var category: Category? = null , //mongo relationship between Realm objects
    var photo: String = "" ,
    var latitude: Long = 0 ,
    var longitude: Long = 0 ,
    var owner_id: String = ""
    ) : RealmObject {
    constructor() : this(owner_id = "") {}
    override fun toString() = "Marker($_id, $name, $photo, $latitude, $longitude, $owner_id)"
}
