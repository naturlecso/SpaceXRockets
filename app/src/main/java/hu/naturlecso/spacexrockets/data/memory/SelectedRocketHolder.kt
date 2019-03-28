package hu.naturlecso.spacexrockets.data.memory

import hu.naturlecso.spacexrockets.domain.Rocket
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class SelectedRocketHolder {

    private val selectedRocketProcessor = BehaviorProcessor.create<Rocket>()

    fun set(rocket: Rocket): Completable = Completable.fromAction { selectedRocketProcessor.onNext(rocket) }

    fun get(): Flowable<Rocket> = selectedRocketProcessor;
}
