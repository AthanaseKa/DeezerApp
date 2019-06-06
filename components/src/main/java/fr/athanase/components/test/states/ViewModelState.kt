package fr.athanase.components.test.states

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import fr.athanase.components.test.statefragment.NetworkLiveData
import fr.athanase.components.test.statefragment.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import timber.log.Timber

abstract class ViewModelState<S>(application: Application) : AndroidViewModel(application),
    CoroutineScope by MainScope() {

    abstract var hasOperation: Boolean

    var isOperationFinished: Boolean = false
    var isOperationLaunched: Boolean = false

    private val mainStateLiveData: MutableLiveData<State> = MutableLiveData()
    private val sourcesLiveData: MediatorLiveData<State> = MediatorLiveData()
    private val networkLiveData: NetworkLiveData = NetworkLiveData(application)

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        mainStateLiveData.postValue(State.Error(exception))
        errorLambda(exception)
    }

    protected open var errorLambda: (error: Throwable) -> Unit = {
        Timber.w("${javaClass.name} : An error occurred.")
    }

    protected fun addDataSources(sources: List<LiveData<DataState>>) {

        setLoading()

        sources.forEach { source ->
            sourcesLiveData.addSource(source) { state ->

                if (networkLiveData.value is NetworkState.Disconnected
                    || networkLiveData.value is NetworkState.Connected
                ) {
                    Timber.e("${state.toString()} hasope : $hasOperation finished : $isOperationFinished  launched :$isOperationLaunched")

                    if (hasOperation) {
                        if (!isOperationLaunched) {
                            operation()
                        }
                        if (state is DataState.Content<*>) {
                            setSuccessOrUpdate()
                        }
                    } else {
                    if (state is DataState.Content<*>) {
                        setSuccessOrUpdate()
                    } else {
                        setError(Throwable("NO OPERATION AND NO DATA"))
                    }
                }
                }
            }
        }
    }

    private fun setSuccessOrUpdate() {
//        if (mainStateLiveData.value == State.Success
//            || mainStateLiveData.value == State.Update) {
//            mainStateLiveData.value = State.Update
//        } else {
//            mainStateLiveData.value = State.Success
//        }
    if (mainStateLiveData.value == State.Success
            || mainStateLiveData.value == State.Update) {
            mainStateLiveData.postValue(State.Update)
        } else {
            mainStateLiveData.postValue(State.Success)
        }
    }

    private fun setError(error: Throwable) {
//        mainStateLiveData.value = State.Error(error)
        mainStateLiveData.postValue(State.Error(error))
    }

     fun setLoading() {
        mainStateLiveData.value = State.Loading
         mainStateLiveData.postValue(State.Loading)
         Timber.e("LOOOOAAADDDIIINNNGG")
         if (hasOperation) {
             operation()
         }
    }

    fun checkErrorType() {}

    abstract fun operation()

    fun getStateLiveData(): MutableLiveData<State> = mainStateLiveData

    fun getMediatorLiveData(): MediatorLiveData<State> = sourcesLiveData

    fun getNetworkLiveData(): NetworkLiveData = networkLiveData

    fun getHandler(): CoroutineExceptionHandler = handler

    override fun onCleared() {
        cancel()
    }
}

sealed class DataState {
    class Content<S>(val content: S) : DataState()
    object NoData : DataState()
}