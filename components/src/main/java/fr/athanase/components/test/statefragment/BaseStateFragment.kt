package fr.athanase.components.test.statefragment

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
import fr.athanase.components.databinding.FragmentBaseStateBinding
import fr.athanase.components.test.states.State
import fr.athanase.components.test.states.ViewModelState
import timber.log.Timber

abstract class BaseStateFragment<S, T : ViewModelState<S>> : Fragment() {

    protected lateinit var binding: FragmentBaseStateBinding

    abstract var viewModel: T
    open lateinit var factory: ViewModelProvider.NewInstanceFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBaseStateBinding.inflate(inflater, container, false)
        activity?.let {
            factory = getViewModelFactory(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviders.of(this, factory)
            .get(viewModel::class.java).apply {

                getNetworkLiveData().observe(viewLifecycleOwner, Observer<NetworkState> {

                    handleNetworkState(it)
                })

                getMediatorLiveData().observe(viewLifecycleOwner, Observer<State> {

                })
                getStateLiveData().observe(viewLifecycleOwner, Observer<State> {
                    Timber.e("YAAA")
                    handleState(it)
                })

            }
    }

    open fun handleNetworkState(networkState: NetworkState) {
        when (networkState) {
            is NetworkState.Disconnected -> {
                viewModel.isOperationLaunched = false
            }
            is NetworkState.Connected -> {
                if (!viewModel.isOperationLaunched) {
                    viewModel.setLoading()
                }
            }
        }
    }

    open fun handleState(state: State?) {
        when (state) {
            is State.Loading -> {
                Timber.e(" ----loading----")
                showLoading()
            }
            is State.Error -> {
                Timber.e("----error----")
                Timber.e(state.error.message)
                showError(state.error.message ?: "NO MESSAGE")
            }
            is State.Update -> {
                Timber.e("----update----")
                showUpdate()
            }
            is State.Success -> {
                Timber.e("----successs----")
                showSuccess()
            }
        }
    }

    open fun showLoading() {
        goToFragment(LoadingStateFragment())
    }

    open fun showError(error: String) {
        val fragment = ErrorStateFragment()
        val bundle = Bundle()
        bundle.putString("ERROR", error)
        fragment.arguments
        goToFragment(ErrorStateFragment())
    }

    abstract fun showUpdate()
    abstract fun showSuccess()

    protected fun goToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.success_container, fragment)
        transaction.commit()
    }

    open fun getViewModelFactory(activity: Activity): ViewModelProvider.NewInstanceFactory {
        return ViewModelProvider.AndroidViewModelFactory(activity.application)
    }
}