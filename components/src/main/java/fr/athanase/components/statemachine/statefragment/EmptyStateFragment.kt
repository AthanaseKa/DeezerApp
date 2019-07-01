package fr.athanase.components.statemachine.statefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.components.databinding.FragmentStateEmptyBinding

class EmptyStateFragment: Fragment() {

    private lateinit var binding: FragmentStateEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }
}