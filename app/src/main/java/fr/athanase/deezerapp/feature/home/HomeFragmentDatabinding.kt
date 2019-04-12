package fr.athanase.deezerapp.feature.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import fr.athanase.deezerapp.BR
import fr.athanase.deezerapp.item.album.AlbumItemBinding
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.deezerapp.item.track.TrackItemBinding

class HomeFragmentDatabinding: BaseObservable() {
    @get:Bindable
    var tracks = listOf<TrackItemBinding>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.tracks)
        }
    @get:Bindable
    var albums = listOf<AlbumItemBinding>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.albums)
        }
    @get:Bindable
    var artists = listOf<ArtistItemBinding>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.artists)
        }
    @get:Bindable
    var playlists = listOf<PlaylistItemBinding>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.playlists)
        }
}