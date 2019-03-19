package fr.athanase.deezerapp.feature.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.athanase.backend.feature.album.AlbumDao
import fr.athanase.backend.feature.album.AlbumManager
import fr.athanase.entites.Album
import kotlinx.coroutines.*
import timber.log.Timber

class AlbumFragmentViewModel(id: Long): ViewModel() {
    private val parentJob = Job()

    val album: MutableLiveData<Album> = AlbumDao.observeAlbumById(id)

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            Timber.e(exception)
        }
        GlobalScope.launch(Dispatchers.IO + parentJob + handler) {
            AlbumManager.fetch(id)
        }
    }

    override fun onCleared() {
        parentJob.cancel()
    }
}