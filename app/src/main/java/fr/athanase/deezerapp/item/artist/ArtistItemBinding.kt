package fr.athanase.deezerapp.item.artist

import fr.athanase.components.ItemBinding
import fr.athanase.deezerapp.R
import fr.athanase.entites.Artist

class ArtistItemBinding(artist: Artist): ItemBinding(R.layout.item_model_artist) {
    override val data = ArtistItemBindingData(artist)
    override val actions = ArtistItemBindingAction()

    override fun compareTo(other: ItemBinding): Int {
        val model = other as ArtistItemBinding? ?: return 1
        if (model.data.title != this.data.title) return 1
        if (model.data.picture != this.data.picture) return 1
        return 0
    }
}