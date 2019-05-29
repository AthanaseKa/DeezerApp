package fr.athanase.backend.feature.playlist

import androidx.lifecycle.MutableLiveData
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.observeDataList
import fr.athanase.backend.pojo.playlist.PlaylistRealmObject
import fr.athanase.backend.pojo.playlist.toEntity
import fr.athanase.entites.Playlist

object PlaylistDao {
    fun observe(): MutableLiveData<List<Playlist?>> {
        return DatabaseInstance.deezerRealm.observeDataList(
            {
                where(PlaylistRealmObject::class.java)
            },
            {
                val list: MutableList<Playlist> = mutableListOf()
                for (playlist in it) {
                    list.add(playlist.toEntity())
                }
                list
            })
    }
}