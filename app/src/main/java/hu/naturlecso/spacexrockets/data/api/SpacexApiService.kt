package hu.naturlecso.spacexrockets.data.api

import io.reactivex.Single
import retrofit2.http.GET

interface SpacexApiService {

    @GET("rockets")
    fun allRockets(): Single<List<RocketApiModel>>

    @GET("launches/past")
    fun allLaunches(): Single<List<LaunchApiModel>>
}
