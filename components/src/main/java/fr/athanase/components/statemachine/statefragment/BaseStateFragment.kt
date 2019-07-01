package fr.athanase.components.statemachine.statefragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import fr.athanase.components.R
import fr.athanase.components.databinding.FragmentStateBaseBinding
import fr.athanase.components.statemachine.ViewModelState
import fr.athanase.components.statemachine.states.NetworkState
import fr.athanase.components.statemachine.states.State
import timber.log.Timber

abstract class BaseStateFragment<S> : Fragment() {

    protected lateinit var binding: FragmentStateBaseBinding
    lateinit var viewModel: ViewModelState<S>
        private set

    open lateinit var factory: ViewModelProvider.NewInstanceFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStateBaseBinding.inflate(inflater, container, false)
        activity?.let {
            factory = getViewModelFactory(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviders.of(this, factory)
            .get(ViewModelState::class.java).apply {

                viewModel = this as ViewModelState<S>
                viewModel.addDatabaseSources()

                getNetworkLiveData().observe(viewLifecycleOwner, Observer<NetworkState> {
                    handleNetworkState(it)
                })

                getPageStateLiveData().observe(viewLifecycleOwner, Observer<State> {
                    handleState(it)
                })

                getDatabaseLiveData().observe(viewLifecycleOwner, Observer<State> {
                })
            }
    }

    open fun handleNetworkState(networkState: NetworkState) {
        when (networkState) {
            is NetworkState.Disconnected -> {
                Timber.e("DISCONNECTED")
                viewModel.isOperationLaunched = false
            }
            is NetworkState.Connected -> {
                Timber.e("CONNECTED")
                if (!viewModel.isOperationLaunched && !viewModel.isOperationFinished
                    || viewModel.getPageStateLiveData().value is State.Error) {
                    viewModel.setLoading()
                }
            }
        }
    }

    open fun handleState(state: State?) {
        when (state) {
            is State.Loading -> {
                Timber.e("===loading===")
                showLoading()
            }
            is State.Error -> {
                Timber.e("===error===")
                showError()
            }
            is State.NetworkError -> {
                Timber.e("===network error===")
                showNetworkError()
            }
            is State.Update -> {
                Timber.e("===update===")
                showUpdate()
            }
            is State.Success -> {
                Timber.e("===success===")
                showSuccess()
            }
            is State.Empty -> {
                Timber.e("===empty===")
                showEmpty()
            }
        }
    }

    open fun showLoading() {
        goToFragment(LoadingStateFragment())
    }

    open fun showError() {
        val fragment = ErrorStateFragment()
        goToFragment(fragment)
        //image text buttton (si operation to retry)
        //erreur quand disconnected avec du cache
    }
    private fun showNetworkError() {
        goToFragment(NetworkErrorStateFragment())
    }

    open fun showUpdate() {}
    open fun showEmpty() {
        goToFragment(EmptyStateFragment())
    }

    abstract fun showSuccess()

    protected fun goToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_state_container, fragment)
        transaction.commit()
    }

    open fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return ViewModelProvider.AndroidViewModelFactory(activity.application)
    }
}