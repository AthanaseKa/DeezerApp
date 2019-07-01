package fr.athanase.deezerapp.feature.home

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.components.statemachine.statefragment.BaseStateFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeStateFragment : BaseStateFragment<ChartState>(), CoroutineScope by MainScope() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.clearButton.setOnClickListener {
            Timber.e("click clear")
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

        binding.error.setOnClickListener {
            viewModel.setError(Throwable())
        }

        binding.loading.setOnClickListener {
            viewModel.setLoading()
        }

        binding.empty.setOnClickListener {
            viewModel.setEmpty()
        }

        binding.success.setOnClickListener {
            viewModel.setSuccessOrUpdate()
        }

        binding.fetchState.setOnClickListener {
            Toast.makeText(context, viewModel.getPageStateLiveData().value.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return HomeStateFragmentViewModelFactory(activity.application)
    }


    override fun showSuccess() {
        goToFragment(HomeFragment())
    }
}

