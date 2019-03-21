package fr.athanase.backend.feature.album

import androidx.lifecycle.MutableLiveData
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.fetchSingleItem
import fr.athanase.backend.dao.observeData
import fr.athanase.backend.dao.saveSingleItemTransaction
import fr.athanase.backend.pojo.album.AlbumJson
import fr.athanase.backend.pojo.album.AlbumRealmObject
import fr.athanase.backend.pojo.album.toEntity
import fr.athanase.backend.pojo.album.toRealmObject
import fr.athanase.entites.Album

object AlbumDao {
    internal suspend fun fetchById(id: Long): AlbumRealmObject? {
        return DatabaseInstance.deezerRealm.fetchSingleItem {
            where(AlbumRealmObject::class.java).equalTo("id", id)
        }
    }

    internal suspend fun save(albumJson: AlbumJson) {
        return save(albumJson.toRealmObject())
    }

    internal suspend fun save(albumRealmObject: AlbumRealmObject) {
        return DatabaseInstance.deezerRealm.saveSingleItemTransaction(albumRealmObject)
    }

    fun observeAlbumById(id: Long): MutableLiveData<Album?> {
        return DatabaseInstance.deezerRealm.observeData({
            where(AlbumRealmObject::class.java).equalTo("id", id)
        }, { it.toEntity() })
    }
}