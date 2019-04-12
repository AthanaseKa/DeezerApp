package fr.athanase.deezerapp.item.artist

import androidx.databinding.Bindable
import fr.athanase.components.ItemBindingData
import fr.athanase.entites.Artist

class ArtistItemBindingData(artist: Artist): ItemBindingData() {
    @get:Bindable
    val title: String = artist.name
    @get:Bindable
    val picture: String = artist.picture ?: ""
}