package fr.athanase.backend.pojo.track

import fr.athanase.backend.pojo.album.AlbumRealmObject
import fr.athanase.backend.pojo.artist.ArtistRealmObject
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class TrackRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var artist: ArtistRealmObject? = null
    var album: AlbumRealmObject? = null
}