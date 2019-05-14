package hu.naturlecso.spacexrockets.ui.launches

import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.common.model.RowViewModel
import hu.naturlecso.spacexrockets.domain.Launch
import hu.naturlecso.spacexrockets.domain.Rocket
import io.reactivex.Flowable

class LaunchListItemLaunchViewModel(launch: Launch) : RowViewModel<Launch>(launch) {
    val launch = RxObservableField(Flowable.just(launch))
}

class LaunchListItemYearViewModel(year: Int) : RowViewModel<Int>(year) {
    val year = RxObservableField(Flowable.just(year))
}

class LaunchListItemChartViewModel(launches: List<Launch>)
    : RowViewModel<List<Launch>>(launches) {
    val chartEntries = RxObservableField<Map<Int, Int>>(Flowable.just(launches)
        .map { launches -> launches.groupBy { it.year } }
        .map { launchMap -> launchMap.mapValues { it.value.count() } }
    )
}

class LaunchListItemRocketDescriptionViewModel(rocket: Rocket) : RowViewModel<Rocket>(rocket) {
    val description = RxObservableField(Flowable.just(rocket)
            .map { it.description }
    )
}
