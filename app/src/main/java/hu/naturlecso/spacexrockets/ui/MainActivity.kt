package hu.naturlecso.spacexrockets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.common.navigation.NavigationCommand
import hu.naturlecso.spacexrockets.common.navigation.Navigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val navigator: Navigator by inject()

    private lateinit var navController: NavController

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navController)

        disposable = navigator.navigationEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is NavigationCommand.To -> navController.navigate(it.directions)
                    is NavigationCommand.Back -> navController.navigateUp()
                }
            }
    }

    override fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        super.onDestroy()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
