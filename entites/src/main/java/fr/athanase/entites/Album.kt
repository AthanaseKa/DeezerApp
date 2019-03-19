package fr.athanase.entites

class Album (
    var id: Long,
    var title: String,
    var cover: String,
    var tracklist: String,
    var nbTracks: Int? = null,
    var artist: Artist
)