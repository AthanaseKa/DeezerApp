package fr.athanase.backend.feature.chart

import androidx.lifecycle.MutableLiveData
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.backend.dao.observeData
import fr.athanase.backend.dao.saveSingleItemTransaction
import fr.athanase.backend.pojo.chart.ChartJson
import fr.athanase.backend.pojo.chart.ChartRealmObject
import fr.athanase.backend.pojo.chart.toEntity
import fr.athanase.backend.pojo.chart.toRealmObject
import fr.athanase.entites.Chart

object ChartDao {
    internal suspend fun save(chartJson: ChartJson) {
        save(chartJson.toRealmObject())
    }

    internal suspend fun save(chart: ChartRealmObject) {
        DatabaseInstance.deezerRealm.saveSingleItemTransaction(chart)
    }

    fun observe(): MutableLiveData<Chart?> {
        return DatabaseInstance.deezerRealm.observeData({
            where(ChartRealmObject::class.java)
        }, { it.toEntity() })
    }
}