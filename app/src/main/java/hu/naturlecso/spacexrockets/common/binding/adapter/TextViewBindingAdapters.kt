package hu.naturlecso.spacexrockets.common.binding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@BindingAdapter("android:text")
fun TextView.bindIntegerAsText(int: Int) {
    text = int.toString()
}

@BindingAdapter("android:text")
fun TextView.bindDateAsText(date: LocalDate?) {
    if (date == null) {
        return
    }

    text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."))
}
