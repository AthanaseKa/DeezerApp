package fr.athanase.deezerapp.feature.album

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import fr.athanase.deezerapp.BR
import fr.athanase.entites.Album

class AlbumFragmentDatabinding: BaseObservable() {
    fun updateAlbum(album: Album) {
        title = album.title
        cover = album.cover
        artistName = album.artist.name
    }
    @get:Bindable
    var title = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    @get:Bindable
    var cover = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cover)
        }
    @get:Bindable
    var artistName = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.artistName)
        }

}