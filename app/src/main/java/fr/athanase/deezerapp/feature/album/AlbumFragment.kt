package fr.athanase.deezerapp.feature.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.athanase.deezerapp.databinding.FragmentAlbumBinding

class AlbumFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val albumId = AlbumFragmentArgs.fromBundle(arguments!!).albumId
        val binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.viewModel = AlbumFragmentDatabinding()
        ViewModelProviders.of(this, AlbumFragmentViewModelFactory(albumId))
            .get(AlbumFragmentViewModel::class.java).apply {
                album.observe(this@AlbumFragment, Observer { album ->
                    album?.let { binding.viewModel?.updateAlbum(it) }
                })
            }
        return binding.root
    }
}