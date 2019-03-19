package fr.athanase.deezerapp.item.artist

import androidx.databinding.Bindable
import fr.athanase.components.ItemModel
import fr.athanase.deezerapp.R
import fr.athanase.entites.Artist

class ArtistItemModel(artist: Artist): ItemModel(R.layout.item_model_artist) {
    @get:Bindable
    val title: String = artist.name
    @get:Bindable
    val picture: String = artist.picture ?: ""

    override fun compareTo(other: ItemModel): Int {
        val model = other as ArtistItemModel? ?: return 1
        if (model.title != this.title) return 1
        if (model.picture != this.picture) return 1
        return 0
    }
}