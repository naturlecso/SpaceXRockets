package hu.naturlecso.spacexrockets.features

import hu.naturlecso.spacexrockets.features.launches.LaunchesViewModel
import hu.naturlecso.spacexrockets.features.rockets.RocketsViewModel
import hu.naturlecso.spacexrockets.features.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { RocketsViewModel(get(), get(), get(), get()) }
    viewModel { LaunchesViewModel(get(), get()) }
    viewModel { WelcomeViewModel(get(), get()) }
}
