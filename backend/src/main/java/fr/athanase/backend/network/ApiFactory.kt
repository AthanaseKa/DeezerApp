package fr.athanase.backend.network

import fr.athanase.backend.feature.album.ApiAlbum
import fr.athanase.backend.feature.chart.ApiChart
import fr.athanase.backend.feature.search.ApiSearch
import fr.athanase.backend.feature.user.ApiUser

internal object ApiFactory{
    val charts: ApiChart = BaseNetwork.retrofit()
        .create(ApiChart::class.java)
    val album: ApiAlbum = BaseNetwork.retrofit()
        .create(ApiAlbum::class.java)
    val search: ApiSearch = BaseNetwork.retrofit()
        .create(ApiSearch::class.java)
    val user: ApiUser = BaseNetwork.retrofit()
        .create(ApiUser::class.java)
}