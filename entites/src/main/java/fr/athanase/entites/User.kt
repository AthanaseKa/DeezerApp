package fr.athanase.entites

class User (
    var id: Int,
    var name: String,
    var country: String?,
    var picture: String?,
    var playlists: List<Playlist>?
)