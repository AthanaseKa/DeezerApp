package fr.athanase.backend.pojo.artist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class ArtistRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
    var picture: String? = null
}