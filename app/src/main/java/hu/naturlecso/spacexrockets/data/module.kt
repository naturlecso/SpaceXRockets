package hu.naturlecso.spacexrockets.data

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.naturlecso.spacexrockets.data.action.DefaultLaunchAction
import hu.naturlecso.spacexrockets.data.action.DefaultRocketAction
import hu.naturlecso.spacexrockets.data.api.SpacexApiService
import hu.naturlecso.spacexrockets.data.database.SpacexDatabase
import hu.naturlecso.spacexrockets.data.store.DefaultLaunchStore
import hu.naturlecso.spacexrockets.data.store.DefaultRocketStore
import hu.naturlecso.spacexrockets.domain.LaunchAction
import hu.naturlecso.spacexrockets.domain.LaunchStore
import hu.naturlecso.spacexrockets.domain.RocketAction
import hu.naturlecso.spacexrockets.domain.RocketStore
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

val dataModule = module {
    val baseUrl = "https://api.spacexdata.com/v3/"
    val contentType = MediaType.get("application/json")
    val cacheSize: Long = 10 * 1024 * 1024

    val databaseName = "spacex.db"

    // api
    single { Cache(get<Application>().cacheDir, cacheSize) }

    single<Interceptor> { HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY } }

    single { OkHttpClient.Builder()
        .cache(get())
        .addInterceptor(get())
        .build() }

    single { Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(get())
        .build() }

    single { get<Retrofit>().create(SpacexApiService::class.java) }

    // database
    single { Room.databaseBuilder(get(), SpacexDatabase::class.java, databaseName).build() }
    single { get<SpacexDatabase>().rocketDao() }
    single { get<SpacexDatabase>().launchDao() }

    // action
    single<RocketAction> { DefaultRocketAction(get(), get()) }
    single<LaunchAction> { DefaultLaunchAction(get(), get()) }

    // store
    single<RocketStore> { DefaultRocketStore(get()) }
    single<LaunchStore> { DefaultLaunchStore(get()) }
}
