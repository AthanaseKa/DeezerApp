package fr.athanase.backend.pojo.user

import fr.athanase.backend.pojo.playlist.PlaylistJson

internal class UserJson(
    var id: Int?,
    var name: String?,
    var country: String?,
    var picture: String?,
    var playlists: List<PlaylistJson>?
)