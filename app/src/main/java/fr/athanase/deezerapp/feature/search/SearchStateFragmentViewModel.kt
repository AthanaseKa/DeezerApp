package fr.athanase.deezerapp.feature.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.athanase.backend.error.ErrorHandler
import fr.athanase.backend.feature.album.AlbumDao
import fr.athanase.backend.feature.artists.ArtistDao
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.backend.feature.playlist.PlaylistDao
import fr.athanase.backend.feature.track.TrackDao
import fr.athanase.components.statemachine.ViewModelState
import fr.athanase.components.statemachine.states.State
import fr.athanase.entites.Album
import fr.athanase.entites.Artist
import fr.athanase.entites.Playlist
import fr.athanase.entites.Track
import timber.log.Timber

class SearchStateFragmentViewModel(application: Application) : ViewModelState<SearchState>(application, ErrorHandler( )) {

    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }
    override var operation: (suspend () -> Unit)? = {
        ChartManager.fetchChart()
    }

    private val _artists: MutableLiveData<List<Artist?>> = ArtistDao.observe()
    val _artistsState: LiveData<SearchState> = Transformations.map(_artists) { artists ->
        return@map if (artists.isNullOrEmpty()) {
            SearchState.UpdateArtist(ArtistState.Empty)
        } else {
            SearchState.UpdateArtist(ArtistState.Content(artists.filterNotNull()))
        }
    }

    private val _albums: MutableLiveData<List<Album?>> = AlbumDao.observe()
    val _albumsState: LiveData<SearchState> = Transformations.map(_albums) { albums ->
        return@map if (albums.isNullOrEmpty()) {
            SearchState.UpdateAlbum(AlbumState.Empty)
        } else {
            SearchState.UpdateAlbum(AlbumState.Content(albums.filterNotNull()))
        }
    }

    private val _playlist: MutableLiveData<List<Playlist?>> = PlaylistDao.observe()
    val _playlistState: LiveData<SearchState> = Transformations.map(_playlist) {
        return@map if (it.isNullOrEmpty()) {
            SearchState.UpdatePlaylist(PlaylistState.Empty)
        } else {
            SearchState.UpdatePlaylist(PlaylistState.Content(it.filterNotNull()))
        }
    }

    private val _tracks: MutableLiveData<List<Track?>> = TrackDao.observe()
    val _tracksState: LiveData<SearchState> = Transformations.map(_tracks) {
        return@map if (it.isNullOrEmpty()) {
            SearchState.UpdateTracks(TrackState.Empty)
        } else {
            SearchState.UpdateTracks(TrackState.Content(it.filterNotNull()))
        }
    }

    override var sources: List<LiveData<SearchState>> =
        listOf(_albumsState, _artistsState, _playlistState, _tracksState)

    override fun displayLogic(): State {

        if (_albumsState.value != null && _tracksState.value != null && _playlistState.value != null && _artistsState.value != null) {
            val albums = _albumsState.value as SearchState.UpdateAlbum
            val tracks = _tracksState.value as SearchState.UpdateTracks
            val playlist = _playlistState.value as SearchState.UpdatePlaylist
            val artist = _artistsState.value as SearchState.UpdateArtist

            return if (albums.albums is AlbumState.Empty
                && tracks.tracks is TrackState.Empty
                && playlist.playlists is PlaylistState.Empty
                && artist.artist is ArtistState.Empty
            ) {
                State.Empty
            } else {
                State.Success
            }
        } else {
            return State.Empty
        }
    }
}