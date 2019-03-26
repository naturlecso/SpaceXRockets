package hu.naturlecso.spacexrockets.common.binding

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class RxObservableField<T> constructor(source: Flowable<T>) : ObservableField<T>() {
    private val disposables = HashMap<Observable.OnPropertyChangedCallback, Disposable>()

    private val source: Flowable<T> = source
        .doOnNext { super.set(it) }
        .doOnError { e -> Timber.e(e, "onError in source observable") }
        .onErrorResumeNext(Flowable.empty())
        .share()

    @Synchronized
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        super.addOnPropertyChangedCallback(callback)
        disposables[callback] = source.subscribe()
    }

    @Synchronized
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        super.removeOnPropertyChangedCallback(callback)

        val disposable = disposables.remove(callback)

        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun set(value: T) = throw UnsupportedOperationException()
}
