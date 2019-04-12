package fr.athanase.deezerapp.item.album

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import androidx.navigation.Navigation
import fr.athanase.components.ItemBindingActions
import fr.athanase.deezerapp.feature.BottomNavigationFragmentDirections

class AlbumItemBindingAction(
    private val id: Long
): ItemBindingActions() {
    fun onAlbumClick(view: View) {

        lateinit var activity: Activity
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                activity = context
            }
            context = context.baseContext
        }
        Navigation.findNavController(activity, fr.athanase.deezerapp.R.id.deezerNavFragment).navigate(
            BottomNavigationFragmentDirections.actionBottomFragmentToAlbumFragment(id))
    }
}