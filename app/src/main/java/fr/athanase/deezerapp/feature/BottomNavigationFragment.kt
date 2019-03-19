package fr.athanase.deezerapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import fr.athanase.deezerapp.R
import fr.athanase.deezerapp.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment: Fragment() {
    private lateinit var binding: FragmentBottomNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.bottom_nav_fragment)
        binding.bottomMenu.setupWithNavController(navController)
    }
}