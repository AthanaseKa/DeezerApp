package fr.athanase.backend.pojo.artist

import fr.athanase.entites.Artist

internal fun ArtistJson.toEntity(): Artist {
    return Artist(
        id = id ?: throw Exception(),
        name = name ?: throw Exception(),
        picture = picture
    )
}

internal fun ArtistJson.toRealmObject(): ArtistRealmObject {
    return ArtistRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception()
        name = this@toRealmObject.name ?: throw Exception()
        picture = this@toRealmObject.picture
    }
}

internal fun ArtistRealmObject.toEntity(): Artist {
    return Artist(
        id = id,
        name = name,
        picture = picture
    )
}