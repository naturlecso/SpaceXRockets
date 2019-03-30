package hu.naturlecso.spacexrockets.ui.binding

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import hu.naturlecso.spacexrockets.R

@BindingAdapter("entries")
fun LineChart.bindEntries(entries: Map<Int, Int>?) {
    if (entries == null) {
        return
    }

    val lineColor = ContextCompat.getColor(context, R.color.colorPrimary)

    entries.map { Entry(it.key.toFloat(), it.value.toFloat()) }
            .let { LineDataSet(it, "").apply {
                lineWidth = 3f
                valueTextSize = 10f
                color = lineColor
                setCircleColor(lineColor)
                setDrawCircleHole(true)
            } }
            .let { LineData(it) }
            .apply {
                data = this
                invalidate()
            }
}
