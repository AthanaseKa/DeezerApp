package fr.athanase.components.test.statefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.athanase.components.databinding.FragmentStateLoadingBinding

class LoadingStateFragment: Fragment() {

    private lateinit var binding: FragmentStateLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }
}