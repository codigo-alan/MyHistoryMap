package com.example.grafitismap.models

import io.realm.kotlin.mongodb.User


fun entityToModel(entity: MarkerEntity): MarkerModel {
    return MarkerModel(
        entity.name,
        entity.category.toString(),
        entity.photo,
        entity.latitude.toDouble(),
        entity.longitude.toDouble()
    )
}

fun modelToEntity(newMarkerModel: MarkerModel, user: User): MarkerEntity {
    val category = Category(name = newMarkerModel.category)
    return MarkerEntity(
        name = newMarkerModel.name,
        category = category,//TODO verify
        photo = newMarkerModel.photo,
        latitude = newMarkerModel.latitude.toString(),
        longitude = newMarkerModel.longitude.toString(),
        owner_id = user.id
    )
}
