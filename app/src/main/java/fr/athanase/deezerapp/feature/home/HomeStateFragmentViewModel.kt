package fr.athanase.deezerapp.feature.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import fr.athanase.backend.error.ErrorHandler
import fr.athanase.backend.feature.chart.ChartDao
import fr.athanase.backend.feature.chart.ChartManager
import fr.athanase.components.statemachine.ViewModelState
import fr.athanase.components.statemachine.states.State
import timber.log.Timber

class HomeStateFragmentViewModel(application: Application) : ViewModelState<ChartState>(application, ErrorHandler()) {

    override var errorLambda: (error: Throwable) -> Unit = {
        Timber.e(it)
    }
    override var operation: (suspend () -> Unit)? = {
        ChartManager.fetchChart()
    }

    val chartState: LiveData<ChartState> = Transformations.map(ChartDao.observe()) {
        return@map if (it == null) {
            ChartState.Empty
        } else {
            ChartState.UpdateCharts(it)
        }
    }

    override var sources: List<LiveData<ChartState>> = listOf(chartState)

    override fun displayLogic(): State {
        if (chartState.value is ChartState.Empty) {
            return State.Empty
        }
        return State.Success
    }
}