package fr.athanase.deezerapp.item.album

import androidx.databinding.Bindable
import fr.athanase.components.ItemModel
import fr.athanase.deezerapp.R
import fr.athanase.entites.Album

class AlbumItemModel(val album: Album): ItemModel(R.layout.item_model_album) {
    @get:Bindable
    val title: String = "${album.title} by ${album.artist.name}" // TODO user translation
    @get:Bindable
    val picture: String = album.cover

    override fun compareTo(other: ItemModel): Int {
        val model = other as AlbumItemModel? ?: return 1
        if (model.title != this.title) return 1
        if (model.picture != this.picture) return 1
        return 0
    }
}