package fr.athanase.backend.pojo.chart

import fr.athanase.backend.pojo.album.AlbumRealmObject
import fr.athanase.backend.pojo.artist.ArtistRealmObject
import fr.athanase.backend.pojo.playlist.PlaylistRealmObject
import fr.athanase.backend.pojo.track.TrackRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class ChartRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var tracks = RealmList<TrackRealmObject>()
    var albums = RealmList<AlbumRealmObject>()
    var artists = RealmList<ArtistRealmObject>()
    var playlists = RealmList<PlaylistRealmObject>()
}