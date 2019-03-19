package fr.athanase.backend.feature.album

import fr.athanase.backend.pojo.album.AlbumJson
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ApiAlbum {
    @GET("album/{id}")
    fun getAlbumAsync(@Path("id") id: Long): Deferred<AlbumJson>
}