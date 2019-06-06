package fr.athanase.deezerapp.test

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.components.test.statefragment.BaseStateFragment
import fr.athanase.deezerapp.feature.home.HomeFragment
import fr.athanase.deezerapp.feature.search.SearchFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeStateFragment : BaseStateFragment<ChartState, HomeStateFragmentViewModel>(),
    CoroutineScope by MainScope() {

    override lateinit var viewModel: HomeStateFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            viewModel = HomeStateFragmentViewModel(it.application)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clearButton.setOnClickListener {
            Timber.e("click clear")
            Timber.e("finished:${viewModel.isOperationFinished} launched:${viewModel.isOperationLaunched} ")
            launch(Dispatchers.IO) {
                ChartManager.flush()
            }
        }

        binding.fetchButton.setOnClickListener {
            Timber.e("click fetch")
            launch(Dispatchers.IO + viewModel.getHandler()) {
                ChartManager.forcefetch()
            }
        }
    }

    override fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return HomeStateFragmentViewModelFactory(activity.application)
    }

    override fun showUpdate() {

    }

    override fun showSuccess() {
        goToFragment(HomeFragment())
    }
}

class HomeStateFragment2 : BaseStateFragment<SearchState, HomeStateFragmentViewModel2>(),
    CoroutineScope by MainScope() {

    override lateinit var  viewModel : HomeStateFragmentViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            viewModel = HomeStateFragmentViewModel2(it.application)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clearButton.setOnClickListener {
            Timber.e("click clear")
            Timber.e("finished:${viewModel.isOperationFinished} launched:${viewModel.isOperationLaunched} ")
            launch(Dispatchers.IO) {
                ChartManager.flush()
            }
        }

        binding.fetchButton.setOnClickListener {
            Timber.e("click fetch")
            launch(Dispatchers.IO + viewModel.getHandler()) {
                ChartManager.forcefetch()
            }
        }
    }

    override fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return HomeStateFragmentViewModelFactory2(activity.application)
    }

    override fun showSuccess() {
        goToFragment(SearchFragment())
    }

    override fun showUpdate() {

    }
}