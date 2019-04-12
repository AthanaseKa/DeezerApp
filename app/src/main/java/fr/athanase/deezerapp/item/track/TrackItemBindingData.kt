package fr.athanase.deezerapp.item.track

import androidx.databinding.Bindable
import fr.athanase.components.ItemBindingData
import fr.athanase.entites.Track

class TrackItemBindingData(track: Track): ItemBindingData() {
    @get:Bindable
    val title: String = "${track.artist.name} - ${track.title}"
    @get:Bindable
    val picture: String = track.album.cover
}