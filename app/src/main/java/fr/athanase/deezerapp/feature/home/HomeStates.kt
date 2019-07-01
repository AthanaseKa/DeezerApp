package fr.athanase.deezerapp.feature.home

import fr.athanase.entites.Chart

sealed class ChartState {
    class UpdateCharts(val chart: Chart) : ChartState()
    object Empty : ChartState()
}