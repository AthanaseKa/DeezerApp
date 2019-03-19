package fr.athanase.backend.pojo.playlist

import fr.athanase.backend.pojo.title.TitleRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class PlaylistRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var picture: String = ""
    var nbTracks: Int = 0
    var description: String? = null
    var creatorName: String? = null
    var titles = RealmList<TitleRealmObject>()
}
