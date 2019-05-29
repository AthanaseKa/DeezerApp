package fr.athanase.deezerapp.feature.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import fr.athanase.components.BR
import fr.athanase.deezerapp.item.album.AlbumItemBinding
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.deezerapp.item.track.TrackItemBinding

class SearchDataBinding : BaseObservable() {


    @get:Bindable
    var showAlbums: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showAlbums)
        }

    @get:Bindable
    var showArtists: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showArtists)
        }

    @get:Bindable
    var showPlaylists: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showPlaylists)
        }
    @get:Bindable
    var showTracks: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showTracks)
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

    @get:Bindable
    var tracks = listOf<TrackItemBinding>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.tracks)
        }
}