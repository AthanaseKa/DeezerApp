package fr.athanase.backend.pojo.album

import fr.athanase.backend.pojo.artist.ArtistRealmObject
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class AlbumRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var isFull: Boolean = false
    var title: String = ""
    var cover: String = ""
    var tracklist: String? = null
    var nb_tracks: Int? = null
    var artist: ArtistRealmObject? = null
}