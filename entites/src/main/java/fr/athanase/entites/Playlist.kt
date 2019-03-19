package fr.athanase.entites

class Playlist (
    var id: Long,
    var title: String,
    var picture: String,
    var nbTracks: Int,
    var description: String?,
    var creatorName: String?,
    var titleRealmList: List<Title>?
)
