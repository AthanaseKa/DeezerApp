package fr.athanase.deezerapp.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeStateFragmentViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeStateFragmentViewModel() as T
    }
}

class HomeStateFragmentViewModelFactory2: ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeStateFragmentViewModel2() as T
    }
}












