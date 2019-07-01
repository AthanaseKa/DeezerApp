package fr.athanase.deezerapp.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.athanase.components.GenericAdapter
import fr.athanase.components.RecyclerViewItemDecoration
import fr.athanase.deezerapp.databinding.FragmentSearchBinding
import fr.athanase.deezerapp.item.album.AlbumItemBinding
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.deezerapp.item.track.TrackItemBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var searchStateFragment: SearchStateFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.data = SearchDataBinding()
        binding.tracks.adapter = GenericAdapter()
        binding.tracks.addItemDecoration(RecyclerViewItemDecoration(10))
        binding.albums.adapter = GenericAdapter()
        binding.albums.addItemDecoration(RecyclerViewItemDecoration(10))
        binding.artists.adapter = GenericAdapter()
        binding.artists.addItemDecoration(RecyclerViewItemDecoration(10))
        binding.playlists.adapter = GenericAdapter()
        binding.playlists.addItemDecoration(RecyclerViewItemDecoration(10))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchStateFragment = parentFragment as SearchStateFragment

        searchStateFragment?.let { parent ->

            ViewModelProviders.of(parent, parent.factory)
                .get(SearchStateFragmentViewModel::class.java).apply {

                    _albumsState.observe(parent, Observer {
                        updateData(it)
                    })

                    _artistsState.observe(parent, Observer {
                        updateData(it)
                    })

                    _playlistState.observe(parent, Observer {
                        updateData(it)
                    })

                    _tracksState.observe(parent, Observer {
                        updateData(it)
                    })
                }
        } ?: throw Throwable("Parent fragment was not found")
    }

    private fun updateData(state: SearchState) {
        when (state) {
            is SearchState.UpdateArtist -> handleArtists(state.artist)
            is SearchState.UpdateAlbum -> handleAlbums(state.albums)
            is SearchState.UpdatePlaylist -> handlePlaylist(state.playlists)
            is SearchState.UpdateTracks -> handleTracks(state.tracks)
        }
    }

    private fun handleTracks(state: TrackState) {
        when (state) {
            is TrackState.Empty -> {
                binding.data?.showTracks = false
            }
            is TrackState.Content -> {
                binding.data?.showTracks = true
                binding.data?.tracks = state.tracks.map { TrackItemBinding(it) }
            }
        }
    }

    private fun handlePlaylist(state: PlaylistState) {
        when (state) {
            is PlaylistState.Empty -> {
                binding.data?.showPlaylists = false
            }
            is PlaylistState.Content -> {
                binding.data?.showPlaylists = true
                binding.data?.playlists = state.playlists.map { PlaylistItemBinding(it) }
            }
        }
    }

    private fun handleArtists(state: ArtistState) {
        when (state) {
            is ArtistState.Empty -> {
                binding.data?.showArtists = false
            }
            is ArtistState.Content -> {
                binding.data?.showArtists = true
                binding.data?.artists = state.artist.map { ArtistItemBinding(it) }
            }
        }
    }

    private fun handleAlbums(state: AlbumState) {
        when (state) {
            is AlbumState.Empty -> {
                binding.data?.showAlbums = false
            }
            is AlbumState.Content -> {
                binding.data?.showAlbums = true
                binding.data?.albums = state.albums.map { AlbumItemBinding(it) }
            }
        }
    }
}