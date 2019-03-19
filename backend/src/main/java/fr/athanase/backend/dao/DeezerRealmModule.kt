package fr.athanase.backend.dao

import fr.athanase.backend.pojo.album.AlbumRealmObject
import fr.athanase.backend.pojo.artist.ArtistRealmObject
import fr.athanase.backend.pojo.chart.ChartRealmObject
import fr.athanase.backend.pojo.playlist.PlaylistRealmObject
import fr.athanase.backend.pojo.title.TitleRealmObject
import fr.athanase.backend.pojo.track.TrackRealmObject
import io.realm.annotations.RealmModule

@RealmModule(
    library = true,
    classes = [
        AlbumRealmObject::class,
        ArtistRealmObject::class,
        ChartRealmObject::class,
        PlaylistRealmObject::class,
        TitleRealmObject::class,
        TrackRealmObject::class
    ])
class DeezerRealmModule