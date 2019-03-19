package fr.athanase.backend.pojo.title

import fr.athanase.entites.Title

internal fun TitleJson.toEntity(): Title {
    return Title(
        id = id ?: throw Exception(""),
        duration = duration ?: throw Exception(""),
        title = title ?: throw Exception(""),
        creatorName = creatorName ?: throw Exception("")
    )
}

internal fun TitleJson.toRealmObject(): TitleRealmObject {
    return TitleRealmObject().apply {
        id = this@toRealmObject.id ?: throw Exception("")
        duration = this@toRealmObject.duration ?: throw Exception("")
        title = this@toRealmObject.title ?: throw Exception("")
        creatorName = this@toRealmObject.creatorName ?: throw Exception("")
    }
}

internal fun TitleRealmObject.toEntity(): Title {
    return Title(
        id = id,
        duration = duration,
        title = title,
        creatorName = creatorName
    )
}