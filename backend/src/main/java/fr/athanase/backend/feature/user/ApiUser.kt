package fr.athanase.backend.feature.user

import fr.athanase.backend.pojo.user.UserRealmObject
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ApiUser {
    @GET("user/{userId}")
    suspend fun getUserAsync(@Path("userId") user: Int): UserRealmObject
}