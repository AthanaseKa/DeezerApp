package fr.athanase.backend.feature.artists

import androidx.lifecycle.MutableLiveData
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.observeDataList
import fr.athanase.backend.pojo.artist.ArtistRealmObject
import fr.athanase.backend.pojo.artist.toEntity
import fr.athanase.entites.Artist

object ArtistDao {
    fun observe(): MutableLiveData<List<Artist?>> {
        return DatabaseInstance.deezerRealm.observeDataList(
            {
                where(ArtistRealmObject::class.java)
            },
            {
                val list: MutableList<Artist> = mutableListOf()
                for (artist in it) {
                    list.add(artist.toEntity())
                }
                list
            })
    }
}