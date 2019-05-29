package fr.athanase.deezerapp.test

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.athanase.backend.feature.album.AlbumDao
import fr.athanase.backend.feature.artists.ArtistDao
import fr.athanase.backend.feature.chart.ChartDao
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.backend.feature.playlist.PlaylistDao
import fr.athanase.backend.feature.track.TrackDao
import fr.athanase.components.test.states.DataState
import fr.athanase.components.test.states.ViewModelState
import fr.athanase.components.test.states.ViewModelState2
import fr.athanase.entites.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeStateFragmentViewModel : ViewModelState<ChartState, Chart?>() {
    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }
    override var lambda: suspend () -> Unit = {
//        delay(2000)
//        ChartManager.fetchChart()
    }

    override var source: MutableLiveData<Chart?> = ChartDao.observe()

    override var dataState: (state: Chart?) -> Unit = {
//        if (it?.artists?.isNotEmpty() == true || it?.albums?.isNotEmpty() == true) {
//            liveData.value = State.Success<ChartState>(ChartState.ShowContent(it))
//        } else if (it?.artists?.isEmpty() == true || it?.albums?.isEmpty() == true) {
//            liveData.value = State.Success<ChartState>(ChartState.ShowEmptyState)
//        }
    }

    init {
        init()
    }

}

class HomeStateFragmentViewModel2(application: Application) : ViewModelState2<SearchState>(application) {

    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }

    private val _artists: MutableLiveData<List<Artist?>> = ArtistDao.observe()
    val _artistsState: LiveData<DataState> = Transformations.map(_artists) { artists ->
        return@map if (artists.isNullOrEmpty()) {
//            if (hasOperation) {
//                DataState.NoData
//            } else {
//                DataState.Content(SearchState.UpdateArtist(ArtistState.Empty))
//            }
//        } else {
//            DataState.Content(SearchState.UpdateArtist(ArtistState.Content(artists.filterNotNull())))
//        }
    }

    private val _albums: MutableLiveData<List<Album?>> = AlbumDao.observe()
    val _albumsState: LiveData<SearchState.UpdateAlbum> = Transformations.map(_albums) { albums ->
        return@map if (albums.isNullOrEmpty()) {
            SearchState.UpdateAlbum(AlbumState.Empty)
        } else {
            SearchState.UpdateAlbum(AlbumState.Content(albums.filterNotNull()))
        }
    }

    private val _playlist: MutableLiveData<List<Playlist?>> = PlaylistDao.observe()
    val _playlistState: LiveData<SearchState.UpdatePlaylist> = Transformations.map(_playlist) {
        return@map if (it == null || it.isNullOrEmpty()) {
            SearchState.UpdatePlaylist(PlaylistState.Empty)
        } else {
            SearchState.UpdatePlaylist(PlaylistState.Content(it.filterNotNull()))
        }
    }

    private val _tracks: MutableLiveData<List<Track?>> = TrackDao.observe()
    val _tracksState: LiveData<SearchState.UpdateTracks> = Transformations.map(_tracks) {
        return@map if (it.isNullOrEmpty()) {
            SearchState.UpdateTracks(TrackState.Empty)
        } else {
            SearchState.UpdateTracks(TrackState.Content(it.filterNotNull()))
        }
    }

    init {
        addDataSources(listOf(_albumsState, _artistsState, _playlistState, _tracksState))
    }

    override fun operation() {

//        hasOperation = true

        launch(Dispatchers.IO + getHandler()) {
            delay(5000)
            ChartManager.fetchChart()
        }

//        hasOperation = false
    }
}