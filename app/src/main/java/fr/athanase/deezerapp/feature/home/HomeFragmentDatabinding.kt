package fr.athanase.deezerapp.feature.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import fr.athanase.deezerapp.BR
import fr.athanase.deezerapp.item.album.AlbumItemModel
import fr.athanase.deezerapp.item.artist.ArtistItemModel
import fr.athanase.deezerapp.item.playlist.PlaylistItemModel
import fr.athanase.deezerapp.item.track.TrackItemModel

class HomeFragmentDatabinding: BaseObservable() {
    @get:Bindable
    var tracks = listOf<TrackItemModel>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.tracks)
        }
    @get:Bindable
    var albums = listOf<AlbumItemModel>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.albums)
        }
    @get:Bindable
    var artists = listOf<ArtistItemModel>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.artists)
        }
    @get:Bindable
    var playlists = listOf<PlaylistItemModel>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.playlists)
        }
}