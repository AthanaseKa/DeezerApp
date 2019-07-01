package fr.athanase.deezerapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.athanase.components.GenericAdapter
import fr.athanase.components.RecyclerViewItemDecoration
import fr.athanase.deezerapp.databinding.FragmentHomeBinding
import fr.athanase.deezerapp.item.album.AlbumItemBinding
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.entites.Chart
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var homeStateFragment: HomeStateFragment? = null

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

        homeStateFragment = parentFragment as HomeStateFragment

        homeStateFragment?.let { parent ->
            ViewModelProviders.of(parent, parent.factory)
                .get(HomeStateFragmentViewModel::class.java).apply {
                    chartState.observe(parent, Observer {
                        Timber.e(it.toString())
                        showSuccess(it)
                    })
                }
        } ?: throw Throwable("Parent fragment was not found")
    }

    fun showSuccess(chart: ChartState?) {
        when (chart) {
            is ChartState.UpdateCharts -> showContent(chart.chart)
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
        binding.data?.showArtists = false
        binding.data?.showAlbums = false
        binding.data?.showTracks = false
        binding.data?.showPlaylists = false
    }
}