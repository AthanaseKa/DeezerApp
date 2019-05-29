package fr.athanase.deezerapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.components.GenericAdapter
import fr.athanase.components.RecyclerViewItemDecoration
import fr.athanase.deezerapp.databinding.FragmentHomeBinding
import fr.athanase.deezerapp.item.album.AlbumItemBinding
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.deezerapp.test.ChartState
import fr.athanase.entites.Chart
import timber.log.Timber


class HomeFragment : Fragment() {

    public fun setState(state: ChartState) {
        this.chartState = state
    }

    var chartState: ChartState? = null

     private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.data = HomeFragmentDatabinding()
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

        //handleState(chartState)
        showSuccess(chartState)

    }

     fun showSuccess(chart: ChartState?) {
        when(chart) {
            is ChartState.ShowEmptyState -> showEmptyState()
            is ChartState.ShowContent -> showContent(chart.chart)
            else -> showEmptyState()
        }
    }

    private fun showContent(chart: Chart) {
        binding.data?.showArtists = true
        binding.data?.showAlbums = true
        binding.data?.showTracks = true
        binding.data?.showPlaylists = true
        binding.data?.albums = chart.albums.map { AlbumItemBinding(it) }
        binding.data?.artists = chart.artists.map { ArtistItemBinding(it) }
        binding.data?.playlists = chart.playlists.map { PlaylistItemBinding(it) }
    }

    private fun showEmptyState() {
        Timber.e("EMPTY")
    }
}