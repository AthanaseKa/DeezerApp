package fr.athanase.backend.pojo.album

import fr.athanase.backend.pojo.artist.toEntity
import fr.athanase.backend.pojo.artist.toRealmObject
import fr.athanase.entites.Album

internal fun AlbumJson.toEntity(): Album {
    return Album(
        id = id ?: throw Throwable("Album must have an id"),
        title = title ?: throw Exception("Album must have an title"),
        cover = cover ?: throw Exception("Album must have an cover"),
        nbTracks = nb_tracks,
        tracklist = tracklist ?: throw Exception("Album must have a tracklist"),
        artist = artist?.toEntity() ?: throw Exception("Album must have an artist")
    )
}

internal fun AlbumJson.toRealmObject(isFull: Boolean = false): AlbumRealmObject {
    return AlbumRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception("Album must have an id")
        this.isFull = isFull
        title = this@toRealmObject.title ?: throw Exception("Album must have an title")
        cover = this@toRealmObject.cover ?: throw Exception("Album must have an cover")
        nb_tracks = this@toRealmObject.nb_tracks
        tracklist = this@toRealmObject.tracklist ?: throw Exception("Album must have a tracklist")
        artist = this@toRealmObject.artist?.toRealmObject()
    }
}

internal fun AlbumRealmObject.toEntity(): Album {
    return Album(
        id = id,
        title = title,
        cover = cover,
        nbTracks = nb_tracks,
        tracklist = tracklist ?: throw Exception("Album must have a tracklist"),
        artist = artist?.toEntity() ?: throw Exception("Album must have an artist")
    )
}