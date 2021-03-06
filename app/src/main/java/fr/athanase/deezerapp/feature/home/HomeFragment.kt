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
import fr.athanase.deezerapp.item.album.AlbumItemBindingData
import fr.athanase.deezerapp.item.artist.ArtistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBinding
import fr.athanase.deezerapp.item.playlist.PlaylistItemBindingData
import fr.athanase.entites.Chart

class HomeFragment: Fragment() {
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

        ViewModelProviders.of(this, HomeFragmentViewModelFactory())
            .get(HomeFragmentViewModel::class.java).apply {
                chart.observe(this@HomeFragment, Observer<Chart?> { chart ->
                    binding.data?.albums = chart?.albums?.map { AlbumItemBinding(it) } ?: listOf()
                    binding.data?.artists = chart?.artists?.map { ArtistItemBinding(it) } ?: listOf()
                    binding.data?.playlists = chart?.playlists?.map { PlaylistItemBinding(it) } ?: listOf()
                })
            }
    }
}