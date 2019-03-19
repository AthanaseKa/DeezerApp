package fr.athanase.deezerapp.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.athanase.backend.feature.chart.ChartDao
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.entites.Chart
import kotlinx.coroutines.*
import timber.log.Timber

class HomeFragmentViewModel: ViewModel() {
    private val parentJob = Job()

    val chart: MutableLiveData<Chart> = ChartDao.observe()

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            Timber.e(exception)
        }
        GlobalScope.launch(Dispatchers.IO + parentJob + handler) {
            ChartManager.fetchChart()
        }
    }

    override fun onCleared() {
        parentJob.cancel()
    }
}