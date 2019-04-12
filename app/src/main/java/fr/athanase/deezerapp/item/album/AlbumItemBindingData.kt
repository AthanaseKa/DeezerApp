package fr.athanase.deezerapp.item.album

import androidx.databinding.Bindable
import fr.athanase.components.ItemBindingData
import fr.athanase.entites.Album

class AlbumItemBindingData(val album: Album): ItemBindingData() {
    @get:Bindable
    val title: String = "${album.title} by ${album.artist.name}" // TODO user translation
    @get:Bindable
    val picture: String = album.cover
}