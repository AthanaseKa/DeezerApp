package fr.athanase.backend.pojo.chart

import fr.athanase.backend.pojo.album.toEntity
import fr.athanase.backend.pojo.album.toRealmObject
import fr.athanase.backend.pojo.artist.toEntity
import fr.athanase.backend.pojo.artist.toRealmObject
import fr.athanase.backend.pojo.playlist.toEntity
import fr.athanase.backend.pojo.playlist.toRealmObject
import fr.athanase.entites.Chart

internal fun ChartJson.toEntity(): Chart {
    return Chart(
//        tracks = tracks.data?.map { it.toEntity() } ?: throw Exception(""),
        albums = albums.data?.map { it.toEntity() } ?: throw Exception(""),
        artists = artists.data?.map { it.toEntity() } ?: throw Exception(""),
        playlists = playlists.data?.map { it.toEntity() } ?: throw Exception("")
    )
}

internal fun ChartJson.toRealmObject(): ChartRealmObject {
    return ChartRealmObject().apply {
        albums.addAll(this@toRealmObject.albums.data?.map { it.toRealmObject() } ?: throw Exception(""))
//        tracks.addAll(this@toRealmObject.tracks.data?.map { it.toRealmObject() } ?: throw Exception(""))
        artists.addAll(this@toRealmObject.artists.data?.map { it.toRealmObject() } ?: throw Exception(""))
        playlists.addAll(this@toRealmObject.playlists.data?.map { it.toRealmObject() } ?: throw Exception(""))
    }
}

internal fun ChartRealmObject.toEntity(): Chart {
    return Chart(
//        tracks = tracks.map { it.toEntity() },
        albums = albums.map { it.toEntity() },
        artists = artists.map { it.toEntity() },
        playlists = playlists.map { it.toEntity() }
    )
}