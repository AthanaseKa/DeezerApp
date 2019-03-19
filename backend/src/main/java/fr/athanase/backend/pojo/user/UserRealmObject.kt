package fr.athanase.backend.pojo.user

import fr.athanase.backend.pojo.playlist.PlaylistRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class UserRealmObject: RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var country: String? = null
    var picture: String? = null
    var playlists: RealmList<PlaylistRealmObject>? = null
}