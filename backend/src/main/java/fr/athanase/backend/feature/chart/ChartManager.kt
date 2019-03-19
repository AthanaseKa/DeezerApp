package fr.athanase.backend.feature.chart

import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.fetchSingleItem
import fr.athanase.backend.network.ApiFactory
import fr.athanase.backend.pojo.chart.ChartRealmObject
import fr.athanase.backend.pojo.chart.toEntity
import fr.athanase.entites.Chart

object ChartManager {
    suspend fun fetchChart(): Chart {
        val chartRealmObject: ChartRealmObject? = DatabaseInstance.deezerRealm.fetchSingleItem()
        if (chartRealmObject != null) {
            return chartRealmObject.toEntity()
        }
        val chartJson = ApiFactory.charts.getChartAsync().await()
        ChartDao.save(chartJson)
        return chartJson.toEntity()
    }
}