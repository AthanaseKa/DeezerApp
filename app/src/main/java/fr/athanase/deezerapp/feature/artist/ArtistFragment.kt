package fr.athanase.deezerapp.feature.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.deezerapp.databinding.FragmentArtistBinding

class ArtistFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding.root
    }
}