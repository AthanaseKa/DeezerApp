package fr.athanase.deezerapp.test

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.components.test.statefragment.BaseStateFragment
import fr.athanase.components.test.statefragment.BaseStateFragment2
import fr.athanase.deezerapp.feature.home.HomeFragment
import fr.athanase.deezerapp.feature.search.SearchFragment
import fr.athanase.entites.Chart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeStateFragment : BaseStateFragment<ChartState, Chart?, HomeStateFragmentViewModel>() {

    override val viewModel = HomeStateFragmentViewModel()
    override val factory = HomeStateFragmentViewModelFactory()

    override fun showSuccess(state: ChartState) {
        val home = HomeFragment()
        home.setState(state)

        goToFragment(home)
    }
}

class HomeStateFragment2 : BaseStateFragment2<SearchState, HomeStateFragmentViewModel2>(),
    CoroutineScope by MainScope() {

    override val viewModel = HomeStateFragmentViewModel2()
    val fragment = SearchFragment()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clearButton.setOnClickListener {
            Timber.e("click clear" )
            launch(Dispatchers.IO) {
                ChartManager.flush()
            }
        }

        binding.fetchButton.setOnClickListener {
            Timber.e("click fetch")
            launch(Dispatchers.IO) {
                ChartManager.forcefetch()
            }
        }
    }

    override fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return HomeStateFragmentViewModelFactory2()
    }

    override fun showSuccess() {
        goToFragment(fragment)
    }

    override fun showUpdate() {

    }
}