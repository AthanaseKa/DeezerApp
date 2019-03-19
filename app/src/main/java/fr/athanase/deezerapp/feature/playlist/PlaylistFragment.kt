package fr.athanase.deezerapp.feature.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.deezerapp.databinding.FragmentPlaylistBinding

class PlaylistFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }
}