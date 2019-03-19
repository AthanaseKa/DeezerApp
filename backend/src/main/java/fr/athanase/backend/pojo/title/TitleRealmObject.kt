package fr.athanase.backend.pojo.title

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

internal open class TitleRealmObject: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var duration: Int = 0
    var title: String = ""
    var creatorName: String = ""
}