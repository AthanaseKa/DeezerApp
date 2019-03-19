package fr.athanase.backend.pojo.track

import fr.athanase.backend.pojo.album.toEntity
import fr.athanase.backend.pojo.album.toRealmObject
import fr.athanase.backend.pojo.artist.toEntity
import fr.athanase.backend.pojo.artist.toRealmObject
import fr.athanase.entites.Track

internal fun TrackJson.toEntity(): Track {
    return Track(
        id = id ?: throw Exception("A Track must have an id"),
        title = title ?: throw Exception("A Track must have an title"),
        artist = artist?.toEntity() ?: throw Exception("A tTrack must have an Artist"),
        album = album?.toEntity() ?: throw Exception("A tTrack must have an Album")
    )
}

internal fun TrackJson.toRealmObject(): TrackRealmObject {
    return TrackRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception("A Track must have an id")
        title = this@toRealmObject.title ?: throw Exception("A Track must have an title")
        artist = this@toRealmObject.artist?.toRealmObject() ?: throw Exception("A Track must have an Artist")
        album = this@toRealmObject.album?.toRealmObject() ?: throw Exception("A Track must have an Album")
    }
}

internal fun TrackRealmObject.toEntity(): Track {
    return Track(
        id = id,
        title = title,
        artist = artist?.toEntity() ?: throw Exception("A tTrack must have an Artist"),
        album = album?.toEntity() ?: throw Exception("A tTrack must have an Album")
    )
}