package fr.athanase.components.test.states

import android.app.Application
import androidx.lifecycle.*
import fr.athanase.components.test.statefragment.NetworkLiveData
import fr.athanase.components.test.statefragment.NetworkState
import kotlinx.coroutines.*
import timber.log.Timber

abstract class ViewModelState<S, D> : ViewModel(), CoroutineScope by MainScope() {

    val liveData: MediatorLiveData<State> = MediatorLiveData()
    abstract val errorLambda: (error: Throwable) -> Unit
    abstract val lambda: suspend () -> Unit

    abstract val source: MutableLiveData<D>
    abstract val dataState: (state: D) -> Unit

    fun init() {

        liveData.addSource(source, dataState)

        liveData.value = State.Loading

        val handler = CoroutineExceptionHandler { _, exception ->
            liveData.postValue(State.Error(exception))
            errorLambda(exception)
        }

        launch(Dispatchers.IO + handler) {
            lambda()
        }
    }

    override fun onCleared() {
        cancel()
    }
}

abstract class ViewModelState2<S> (application: Application): AndroidViewModel(application), CoroutineScope by MainScope() {

    private var hasSucceeded: Boolean = false
    protected var hasOperation: Boolean = false

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

    protected fun addDataSources(sources: List<LiveData<out S>>) {

        getStateLiveData().value = State.Loading

        operation()

        sources.forEach { source ->
            sourcesLiveData.addSource(source) { state ->

                when (networkLiveData.value) {
                    is NetworkState.Connected -> {
                        if (state.)
                        //if empty
                            // if is loading && online coroutines
                                // keep loading
                            // else
                                // success -> Empty state
                        //else
                            // success show data

                    }
                    is NetworkState.Disconnected -> {
                        //if hasData
                            //show data
                        //else
                            //if network error
                                //show network error and retry online coroutines when online
                            //else
                                //show error

                    }
                }


                if (!hasSucceeded) {
                    mainStateLiveData.value = State.Success
                    hasSucceeded = true
                } else {
                    mainStateLiveData.value = State.Update
                }
            }
        }
    }

//    protected fun checkOperation() {}

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