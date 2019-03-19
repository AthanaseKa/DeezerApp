package fr.athanase.deezerapp.feature.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlbumFragmentViewModelFactory(private val id: Long): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumFragmentViewModel(id) as T
    }
}