package hu.naturlecso.spacexrockets

import hu.naturlecso.spacexrockets.common.navigation.DefaultNavigator
import hu.naturlecso.spacexrockets.common.navigation.Navigator
import org.koin.dsl.module

val appModule = module {
    single<Navigator> { DefaultNavigator() }
}
