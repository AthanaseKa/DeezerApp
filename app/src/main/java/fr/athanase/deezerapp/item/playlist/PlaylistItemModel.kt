package fr.athanase.deezerapp.item.playlist

import androidx.databinding.Bindable
import fr.athanase.components.ItemModel
import fr.athanase.deezerapp.R
import fr.athanase.entites.Playlist

class PlaylistItemModel(playlist: Playlist): ItemModel(R.layout.item_model_playlist) {
    @get:Bindable
    val title: String = "${playlist.title} by ${playlist.creatorName}" // TODO use translation here
    @get:Bindable
    val picture: String = playlist.picture

    override fun compareTo(other: ItemModel): Int {
        val model = other as PlaylistItemModel? ?: return 1
        if (model.title != this.title) return 1
        if (model.picture != this.picture) return 1
        return 0
    }
}