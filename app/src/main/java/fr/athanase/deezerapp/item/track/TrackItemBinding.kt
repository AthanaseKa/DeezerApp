package fr.athanase.deezerapp.item.track

import fr.athanase.components.ItemBinding
import fr.athanase.deezerapp.R
import fr.athanase.entites.Track

class TrackItemBinding(track: Track): ItemBinding(R.layout.item_model_track) {
    override val data = TrackItemBindingData(track)
    override val actions = TrackItemBindingAction()

    override fun compareTo(other: ItemBinding): Int {
        val model = other as TrackItemBinding? ?: return 1
        if (model.data.title != this.data.title) return 1
        if (model.data.picture != this.data.picture) return 1
        return 0
    }
}