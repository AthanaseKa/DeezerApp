package fr.athanase.deezerapp.item.playlist

import androidx.databinding.Bindable
import fr.athanase.components.ItemBindingData
import fr.athanase.entites.Playlist

class PlaylistItemBindingData(playlist: Playlist): ItemBindingData() {
    @get:Bindable
    val title: String = "${playlist.title} by ${playlist.creatorName}" // TODO use translation here
    @get:Bindable
    val picture: String = playlist.picture
}