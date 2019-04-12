package fr.athanase.deezerapp.item.album

import fr.athanase.components.ItemBinding
import fr.athanase.deezerapp.R
import fr.athanase.entites.Album

class AlbumItemBinding(val album: Album): ItemBinding(R.layout.item_model_album) {
    override val data = AlbumItemBindingData(album)
    override val actions = AlbumItemBindingAction(album.id)

    override fun compareTo(other: ItemBinding): Int {
        val model = other as AlbumItemBinding? ?: return 1
        if (model.data.title != this.data.title) return 1
        if (model.data.picture != this.data.picture) return 1
        return 0
    }
}