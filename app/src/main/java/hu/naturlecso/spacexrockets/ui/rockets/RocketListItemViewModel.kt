package hu.naturlecso.spacexrockets.ui.rockets

import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.common.model.RowViewModel
import hu.naturlecso.spacexrockets.domain.Rocket
import io.reactivex.Flowable

class RocketListItemViewModel(rocket: Rocket) : RowViewModel<Rocket>(rocket) {
    val rocket = RxObservableField(Flowable.just(rocket))
}
