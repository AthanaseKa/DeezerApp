package fr.athanase.deezerapp.item.album

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import androidx.navigation.Navigation
import fr.athanase.components.ItemAction
import fr.athanase.deezerapp.feature.BottomNavigationFragmentDirections
import fr.athanase.entites.Album

class AlbumItemAction: ItemAction() {
    fun onAlbumClick(view: View, album: Album) {
        lateinit var activity: Activity
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                activity = context
            }
            context = context.baseContext
        }
        Navigation.findNavController(activity, fr.athanase.deezerapp.R.id.deezerNavFragment)
            .navigate(BottomNavigationFragmentDirections.actionBottomFragmentToAlbumFragment(album.id))
    }
}