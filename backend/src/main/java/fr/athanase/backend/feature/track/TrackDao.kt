package fr.athanase.backend.feature.track

import androidx.lifecycle.MutableLiveData
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.observeDataList
import fr.athanase.backend.pojo.track.TrackRealmObject
import fr.athanase.backend.pojo.track.toEntity
import fr.athanase.entites.Track

object TrackDao {
    fun observe(): MutableLiveData<List<Track?>> {
        return DatabaseInstance.deezerRealm.observeDataList(
            {
                where(TrackRealmObject::class.java)
            },
            {
                val list: MutableList<Track> = mutableListOf()
                for (track in it) {
                    list.add(track.toEntity())
                }
                list
            })
    }
}