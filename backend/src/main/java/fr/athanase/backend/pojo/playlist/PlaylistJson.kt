package fr.athanase.backend.pojo.playlist

import fr.athanase.backend.pojo.title.TitleJson

internal class PlaylistJson(
    var id: Long?,
    var title: String?,
    var picture: String?,
    var nb_tracks: Int?,
    var description: String?,
    var creatorName: String?,
    var titles: List<TitleJson>?
)