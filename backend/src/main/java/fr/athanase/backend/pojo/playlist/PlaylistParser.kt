package fr.athanase.backend.pojo.playlist

import fr.athanase.backend.pojo.title.toEntity
import fr.athanase.backend.pojo.title.toRealmObject
import fr.athanase.entites.Playlist

internal fun PlaylistJson.toEntity(): Playlist {
    return Playlist(
        id = id ?: throw Exception("A playlist must have an id"),
        title = title ?: throw Exception("A playlist must have a title"),
        picture = picture ?: throw Exception("A playlist must have a picture"),
        nbTracks = nb_tracks ?: throw Exception("A playlist must have nbTracks"),
        description = description,
        creatorName = creatorName,
        titleRealmList = titles?.map { it.toEntity() }
    )
}

internal fun PlaylistJson.toRealmObject(): PlaylistRealmObject {
    return PlaylistRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception("A playlist must have an id")
        title = this@toRealmObject.title ?: throw Exception("A playlist must have an id")
        picture = this@toRealmObject.picture ?: throw Exception("A playlist must have an id")
        nbTracks = this@toRealmObject.nb_tracks ?: throw Exception("A playlist must have an id")
        description = this@toRealmObject.description
        creatorName = this@toRealmObject.creatorName
        this@toRealmObject.titles?.let { realmTitles -> titles.addAll(realmTitles.map { it.toRealmObject() }) }
    }
}

internal fun PlaylistRealmObject.toEntity(): Playlist {
    return Playlist(
        id = id,
        title = title,
        picture = picture,
        nbTracks = nbTracks,
        description = description,
        creatorName = creatorName,
        titleRealmList = titles.map { it.toEntity() }
    )
}