package fr.athanase.backend.feature.album

import fr.athanase.backend.network.ApiFactory
import fr.athanase.backend.pojo.album.toEntity
import fr.athanase.backend.pojo.album.toRealmObject
import fr.athanase.entites.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce

object AlbumManager {
    suspend fun fetch(id: Long) {
        val albumRealmObject = AlbumDao.fetchById(id)
        if (albumRealmObject?.isFull == false) {
            val albumJson = ApiFactory.album.getAlbumAsync(id).await()
            AlbumDao.save(albumJson.toRealmObject().apply { isFull = true })
        }
    }

    @ExperimentalCoroutinesApi
    fun CoroutineScope.fetch(id: Long) = produce<Album> {
        val albumDao = AlbumDao.fetchById(id)
        if (albumDao != null) {
            send(albumDao.toEntity())
            if (albumDao.isFull) {
                // ObserveRealm
            }
        }
        else {
            val albumJson = ApiFactory.album.getAlbumAsync(id).await()
            AlbumDao.save(albumJson)
            // ObserveRealm
            send(albumJson.toEntity())
        }
    }
}