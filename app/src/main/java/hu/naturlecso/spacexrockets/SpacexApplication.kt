package hu.naturlecso.spacexrockets

import android.app.Application
import hu.naturlecso.spacexrockets.data.dataModule
import hu.naturlecso.spacexrockets.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SpacexApplication  : Application() {

    override fun onCreate() {
        super.onCreate()

        if  (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@SpacexApplication)

            modules(appModule)
            modules(dataModule)
            modules(uiModule)
        }
    }
}
