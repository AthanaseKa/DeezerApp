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
import fr.athanase.components.test.states.ViewModelState2
import timber.log.Timber

abstract class BaseStateFragment<S, D, T : ViewModelState<S, D>> : Fragment() {

    private lateinit var binding: FragmentBaseStateBinding

    abstract val viewModel: T
    abstract val factory: ViewModelProvider.NewInstanceFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBaseStateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviders.of(this, factory)
            .get(viewModel::class.java).apply {
                liveData.observe(this@BaseStateFragment, Observer<State> {
                    handleState(it)
                })
            }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    open fun handleState(state: State) {
        when (state) {
            is State.Loading -> showLoading()
            is State.Error -> showError()
//            is State.Success<*> -> showSuccess(state.state as S)
        }
    }

    open fun showLoading() {
        goToFragment(LoadingStateFragment())
    }

    open fun showError() {
        goToFragment(ErrorStateFragment())
    }

    abstract fun showSuccess(state: S)

    protected fun goToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.success_container, fragment)
        transaction.commit()
    }
}

abstract class BaseStateFragment2<S, T : ViewModelState2<S>> : Fragment() {

    protected lateinit var binding: FragmentBaseStateBinding

    abstract val viewModel: T
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

                getNetworkLiveData().observe(this@BaseStateFragment2, Observer<NetworkState> {
                    handleNetworkState(it)
                })

                getMediatorLiveData().observe(this@BaseStateFragment2, Observer<State> {})
                getStateLiveData().observe(this@BaseStateFragment2, Observer<State> {
                    handleState(it)
                })

            }
    }

    open fun handleNetworkState(networkState: NetworkState) {
        when (networkState) {
            is NetworkState.Disconnected -> handleState((State.Error(Throwable("NO CONNECTION"))))
            is NetworkState.Connected -> {
//                if (getStateLiveData().value is State.Update
//                    || getStateLiveData().value is State.Success
//                ) {
//                    handleState(State.Success)
//                } else if (getStateLiveData().value is State.Loading) {
//                                    loading()
//                } else {
//                    handleState(getStateLiveData().value)
//                }
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