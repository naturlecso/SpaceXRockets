package hu.naturlecso.spacexrockets.ui

import hu.naturlecso.spacexrockets.ui.launches.LaunchesViewModel
import hu.naturlecso.spacexrockets.ui.rockets.RocketsViewModel
import hu.naturlecso.spacexrockets.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { RocketsViewModel(get(), get(), get(), get()) }
    viewModel { LaunchesViewModel(get(), get()) }
    viewModel { WelcomeViewModel(get(), get()) }
}
