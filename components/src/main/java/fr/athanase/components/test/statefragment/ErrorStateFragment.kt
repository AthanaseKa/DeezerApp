package fr.athanase.components.test.statefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.components.databinding.FragmentStateErrorBinding

class ErrorStateFragment: Fragment() {

    private lateinit var binding: FragmentStateErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateErrorBinding.inflate(inflater, container, false)
        binding.data = arguments?.getString("ERROR")
        return binding.root
    }
}