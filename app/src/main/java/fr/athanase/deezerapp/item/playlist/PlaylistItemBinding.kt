package fr.athanase.deezerapp.item.playlist

import fr.athanase.components.ItemBinding
import fr.athanase.deezerapp.R
import fr.athanase.entites.Playlist

class PlaylistItemBinding(playlist: Playlist): ItemBinding(R.layout.item_model_playlist) {
    override val data = PlaylistItemBindingData(playlist)
    override val actions = PlaylistItemBindingAction()

    override fun compareTo(other: ItemBinding): Int {
        val model = other as PlaylistItemBinding? ?: return 1
        if (model.data.title != this.data.title) return 1
        if (model.data.picture != this.data.picture) return 1
        return 0
    }
}