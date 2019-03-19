package fr.athanase.backend.pojo.album

import fr.athanase.backend.pojo.artist.ArtistJson

internal class AlbumJson(
    var id: Long? = null,
    var title: String? = null,
    var cover: String? = null,
    var tracklist: String? = null,
    var nb_tracks: Int? = null,
    var artist: ArtistJson?
)