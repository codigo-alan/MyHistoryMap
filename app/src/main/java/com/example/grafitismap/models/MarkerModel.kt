package com.example.grafitismap.models

import io.realm.kotlin.mongodb.User
import io.realm.kotlin.types.ObjectId


class MarkerModel (
    var id: ObjectId = ObjectId.create(),
    var name: String,
    var category: String,
    var photo: String,
    var latitude: Double,
    var longitude: Double,
    ){
    fun toEntity(user: User): MarkerEntity {
        val category = Category(name = this.category)
        return MarkerEntity(
            name = this.name,
            category = category,//TODO verify
            photo = this.photo,
            latitude = this.latitude.toString(),
            longitude = this.longitude.toString(),
            owner_id = user.id
        )
    }
}