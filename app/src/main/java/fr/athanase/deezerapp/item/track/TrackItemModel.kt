package fr.athanase.deezerapp.item.track

import androidx.databinding.Bindable
import fr.athanase.components.ItemModel
import fr.athanase.deezerapp.R
import fr.athanase.entites.Track

class TrackItemModel(track: Track): ItemModel(R.layout.item_model_track) {
    @get:Bindable
    val title: String = "${track.artist.name} - ${track.title}"
    @get:Bindable
    val picture: String = track.album.cover

    override fun compareTo(other: ItemModel): Int {
        val model = other as TrackItemModel? ?: return 1
        if (model.title != this.title) return 1
        if (model.picture != this.picture) return 1
        return 0
    }
}