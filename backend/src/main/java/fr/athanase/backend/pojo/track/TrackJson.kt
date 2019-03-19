package fr.athanase.backend.pojo.track

import fr.athanase.backend.pojo.album.AlbumJson
import fr.athanase.backend.pojo.artist.ArtistJson

internal class TrackJson(
    var id: Long?,
    var title: String?,
    var artist: ArtistJson?,
    var album: AlbumJson?
)