package fr.athanase.backend.pojo.chart

import fr.athanase.backend.pojo.album.AlbumJson
import fr.athanase.backend.pojo.api.ApiListResultJson
import fr.athanase.backend.pojo.artist.ArtistJson
import fr.athanase.backend.pojo.playlist.PlaylistJson

internal class ChartJson(
//    var tracks: ApiListResultJson<TrackJson>,
    var albums: ApiListResultJson<AlbumJson>,
    var artists: ApiListResultJson<ArtistJson>,
    var playlists: ApiListResultJson<PlaylistJson>
)