package fr.athanase.backend.feature.chart

import fr.athanase.backend.pojo.chart.ChartJson
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

internal interface ApiChart {
    @GET("chart")
    fun getChartAsync(): Deferred<ChartJson>
}