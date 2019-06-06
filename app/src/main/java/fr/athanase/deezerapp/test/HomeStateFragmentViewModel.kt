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
import fr.athanase.components.test.states.State
import fr.athanase.components.test.states.ViewModelState
import fr.athanase.entites.Album
import fr.athanase.entites.Artist
import fr.athanase.entites.Playlist
import fr.athanase.entites.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeStateFragmentViewModel (application: Application) : ViewModelState<ChartState>(application) {

    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }

    override var hasOperation: Boolean = true

    val chartState: LiveData<DataState> = Transformations.map(ChartDao.observe()) {
        return@map if (it == null) {
            if (hasOperation && !isOperationFinished && isOperationLaunched && getStateLiveData().value is State.Loading) {
                DataState.NoData
            } else {
                DataState.Content(ChartState.Empty)
            }
        } else {
            DataState.Content(ChartState.UpdateCharts(it))
        }
    }

    init {
        addDataSources(listOf(chartState))
    }

    override fun operation() {
        launch(Dispatchers.IO + getHandler()) {
            isOperationLaunched = true
            delay(5000)
            ChartManager.fetchChart()
            isOperationFinished = true
        }
    }
}

class HomeStateFragmentViewModel2(application: Application) : ViewModelState<SearchState>(application) {

    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }

    override var hasOperation: Boolean = true

    private val _artists: MutableLiveData<List<Artist?>> = ArtistDao.observe()
    val _artistsState: LiveData<DataState> = Transformations.map(_artists) { artists ->
        return@map if (artists.isNullOrEmpty()) {
            if (hasOperation && !isOperationFinished && isOperationLaunched && getStateLiveData().value is State.Loading) {
                DataState.NoData
            } else {
                DataState.Content(SearchState.UpdateArtist(ArtistState.Empty))
            }
        } else {
            DataState.Content(SearchState.UpdateArtist(ArtistState.Content(artists.filterNotNull())))
        }
    }

    private val _albums: MutableLiveData<List<Album?>> = AlbumDao.observe()
    val _albumsState: LiveData<DataState> = Transformations.map(_albums) { albums ->
        return@map if (albums.isNullOrEmpty()) {
            if (hasOperation && !isOperationFinished && isOperationLaunched && getStateLiveData().value is State.Loading) {
                DataState.NoData
            } else {
                DataState.Content(SearchState.UpdateAlbum(AlbumState.Empty))
            }
        } else {
            DataState.Content(SearchState.UpdateAlbum(AlbumState.Content(albums.filterNotNull())))
        }
    }

    private val _playlist: MutableLiveData<List<Playlist?>> = PlaylistDao.observe()
    val _playlistState: LiveData<DataState> = Transformations.map(_playlist) {
        return@map if (it.isNullOrEmpty()) {
            if (hasOperation && !isOperationFinished && isOperationLaunched && getStateLiveData().value is State.Loading) {
                DataState.NoData
            } else {
                DataState.Content(SearchState.UpdatePlaylist(PlaylistState.Empty))
            }
        } else {
            DataState.Content(SearchState.UpdatePlaylist(PlaylistState.Content(it.filterNotNull())))
        }
    }

    private val _tracks: MutableLiveData<List<Track?>> = TrackDao.observe()
    val _tracksState: LiveData<DataState> = Transformations.map(_tracks) {
        return@map if (it.isNullOrEmpty()) {
            if (hasOperation && !isOperationFinished && isOperationLaunched && getStateLiveData().value is State.Loading) {
                DataState.NoData
            } else {
                DataState.Content(SearchState.UpdateTracks(TrackState.Empty))
            }
        } else {
            DataState.Content(SearchState.UpdateTracks(TrackState.Content(it.filterNotNull())))
        }
    }

    init {
        addDataSources(listOf(_albumsState, _artistsState, _playlistState, _tracksState))
    }

    override fun operation() {
        launch(Dispatchers.IO + getHandler()) {
            isOperationLaunched = true
            delay(5000)
            ChartManager.fetchChart()
            isOperationFinished = true
        }
    }
}