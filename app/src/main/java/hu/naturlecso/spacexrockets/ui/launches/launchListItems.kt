package hu.naturlecso.spacexrockets.ui.launches

import hu.naturlecso.spacexrockets.domain.Launch
import hu.naturlecso.spacexrockets.domain.Rocket

sealed class LaunchListItem {
    data class LaunchItem(val launch: Launch): LaunchListItem()
    data class Year(val year: Int): LaunchListItem()
    data class Chart(val launches: List<Launch>): LaunchListItem()
    data class RocketDescription(val rocket: Rocket): LaunchListItem()
}
