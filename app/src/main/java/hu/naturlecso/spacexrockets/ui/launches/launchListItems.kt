package hu.naturlecso.spacexrockets.ui.launches

import hu.naturlecso.spacexrockets.domain.Launch

interface LaunchListItem

data class LaunchListItemModel(val launch: Launch) : LaunchListItem

data class LaunchListItemHeader(val year: Int) : LaunchListItem
