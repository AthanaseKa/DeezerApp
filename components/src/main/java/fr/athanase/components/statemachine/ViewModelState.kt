package fr.athanase.components.statemachine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import fr.athanase.components.statemachine.statefragment.NetworkLiveData
import fr.athanase.components.statemachine.states.NetworkState
import fr.athanase.components.statemachine.states.State
import fr.athanase.entites.StateErrorHandler
import fr.athanase.entites.StateErrorType
import kotlinx.coroutines.*

abstract class ViewModelState<S>(application: Application, private val errorHandler: StateErrorHandler) :
    AndroidViewModel(application), CoroutineScope by MainScope() {

    abstract var sources: List<LiveData<S>>

    open var operation: (suspend () -> Unit)? = null

    var isOperationFinished: Boolean = false
    var isOperationLaunched: Boolean = false

    private var isInit: Boolean = false

    private val pageStateLiveData: MutableLiveData<State> = MutableLiveData()
    private val sourcesLiveData: MediatorLiveData<State> = MediatorLiveData()
    private val networkLiveData: NetworkLiveData = NetworkLiveData(application)

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        setError(exception)
        errorLambda(exception)
    }

    protected open var errorLambda: (error: Throwable) -> Unit = {}

    /**
     * Handle the logic determining the child fragment State.
     * Example: If two sources don't have data, State is Empty, but if one source has data, State is Success
     * @return State
     */
    abstract fun displayLogic(): State

    companion object {
        /**
         * Handle the logic and priority between the different States
         * @param networkState
         * @param state State received from displayLogic() implementation should be Success or Empty
         * @param operation is used to check if we should wait for an operation to finish
         * @param isOperationRunning
         */
        fun setPageStateFromDataState(
            networkState: NetworkState?,
            state: State,
            operation: (suspend () -> Unit)?,
            isOperationRunning: Boolean
        ): State? {
            if (networkState is NetworkState.Disconnected
                || networkState is NetworkState.Connected
            ) {
                return if (operation != null) {
                    when (state) {
                        is State.Success -> State.Success
                        is State.Empty -> {
                            if (isOperationRunning) {
                                return State.Loading
                            }
                            State.Empty
                        }
                        else -> state
                    }
                } else {
                    when (state) {
                        is State.Success -> State.Success
                        is State.Error -> State.Error
                        else -> State.Empty
                    }
                }
            } else {
                return null
            }
        }
    }

    /**
     * Handle the Success or Update States's logic
     */
    fun setSuccessOrUpdate() {
        if (pageStateLiveData.value is State.Success
            || pageStateLiveData.value is State.Update
        ) {
            pageStateLiveData.value = State.Update
        } else {
            pageStateLiveData.value = State.Success
        }
    }

    /**
     * Handle the Error State'slogic
     */
    fun setError(error: Throwable) {
        if (pageStateLiveData.value !is State.Success
            && pageStateLiveData.value !is State.Update
        ) {
            when (errorHandler.getErrorType(error)) {
                StateErrorType.NETWORK_ERROR -> {
                    pageStateLiveData.postValue(State.NetworkError)
                    isOperationLaunched = false
                }
                else -> pageStateLiveData.postValue(State.Error)
            }
        }
    }

    /**
     * Handle the Loading State logic
     */
    fun setLoading() {
        if (operation != null && !isOperationLaunched) {
            pageStateLiveData.value = State.Loading
            launchOperation()
        }
    }

    /**
     * Handle the Empty State logic
     */
    fun setEmpty() {
        pageStateLiveData.value = State.Empty
    }

    /**
     * Add all the differents data sources to a MediatorLiveData
     */
    fun addDatabaseSources() {
        if (!isInit) {
            isInit = true
            setLoading()

            sources.forEach { source ->
                sourcesLiveData.addSource(source) {
                    when (setPageStateFromDataState(
                        networkLiveData.value,
                        displayLogic(),
                        operation,
                        hasAnOperationRunning()
                    )) {
                        State.Success -> setSuccessOrUpdate()
                        State.Loading -> {
                        }
                        State.Empty -> setEmpty()
                    }

                }
            }
        }
    }

    /**
     * Launch the operation defined in the child viewmodel
     */
    private fun launchOperation() {
        launch(Dispatchers.IO + handler) {
            operation?.let {
                isOperationLaunched = true
                it()
                isOperationFinished = true
            }
        }
    }

    /**
     * Check if an operation is currently running
     * @return Boolean
     */
    private fun hasAnOperationRunning(): Boolean {
        return (operation != null
                && !isOperationFinished
                && isOperationLaunched
                && pageStateLiveData.value is State.Loading)
    }

    fun getPageStateLiveData(): MutableLiveData<State> = pageStateLiveData

    fun getDatabaseLiveData(): MediatorLiveData<State> = sourcesLiveData

    fun getNetworkLiveData(): NetworkLiveData = networkLiveData

    fun getHandler() : CoroutineExceptionHandler = handler

    override fun onCleared() {
        cancel()
    }
}