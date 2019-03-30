package hu.naturlecso.spacexrockets.ui.launches

import androidx.databinding.ObservableField
import hu.naturlecso.spacexrockets.common.model.RowViewModel
import hu.naturlecso.spacexrockets.domain.Launch

class LaunchListItemModelViewModel(launch: Launch) : RowViewModel<Launch>(launch) {
    val launch = ObservableField(launch)
}

class LaunchListItemHeaderViewModel(year: Int) : RowViewModel<Int>(year) {
    val year = ObservableField(year)
}
