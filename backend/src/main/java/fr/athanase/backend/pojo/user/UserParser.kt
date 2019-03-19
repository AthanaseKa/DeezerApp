package fr.athanase.backend.pojo.user

import fr.athanase.backend.pojo.playlist.PlaylistRealmObject
import fr.athanase.backend.pojo.playlist.toEntity
import fr.athanase.backend.pojo.playlist.toRealmObject
import fr.athanase.entites.User
import io.realm.RealmList

internal fun UserJson.toEntity(): User {
    return User(
        id = id ?: throw Exception("A User must have an id"),
        name = name ?: throw Exception("A User must have an name"),
        country = country,
        picture = picture,
        playlists = playlists?.map { it.toEntity() } ?: throw Exception("A User must have an name")
    )
}

internal fun UserJson.toRealmObject(): UserRealmObject {
    return UserRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception("A User must have an id")
        name = this@toRealmObject.name ?: throw Exception("A User must have an name")
        country = this@toRealmObject.country
        picture = this@toRealmObject.picture
        playlists = this@toRealmObject.playlists?.map { it.toRealmObject() } as RealmList<PlaylistRealmObject>?
            ?: throw Exception("A User must have an name")
    }
}

internal fun UserRealmObject.toEntity(): User {
    return User(
        id = id,
        name = name,
        country = country,
        picture = picture,
        playlists = playlists?.map { it.toEntity() } ?: throw Exception("A User must have an name")
    )
}