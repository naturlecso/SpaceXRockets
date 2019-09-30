package hu.naturlecso.spacexrockets.features.launches

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.domain.model.Launch
import hu.naturlecso.spacexrockets.domain.model.Rocket
import hu.naturlecso.spacexrockets.launchChart
import hu.naturlecso.spacexrockets.launchYear
import hu.naturlecso.spacexrockets.launchItem
import hu.naturlecso.spacexrockets.launchRocketDescription
import hu.naturlecso.spacexrockets.util.ChartEntries

@BindingAdapter("entries")
fun LineChart.bindEntries(entries: ChartEntries?) {
    entries ?: return

    val baseColor = ContextCompat.getColor(context, R.color.colorPrimary)

    xAxis.granularity = 1f
    axisLeft.granularity = 1f
    axisRight.isEnabled = false
    setNoDataTextColor(baseColor)
    legend.isEnabled = false
    description.isEnabled = false

    entries.map { Entry(it.key.toFloat(), it.value.toFloat()) }
            .let { LineDataSet(it, "").apply {
                lineWidth = 3f
                valueTextSize = 10f
                color = baseColor
                setCircleColor(baseColor)
                setDrawCircleHole(true)
            } }
            .let { LineData(it) }
            .apply {
                data = this
                invalidate()
            }
}

@BindingAdapter("items")
fun EpoxyRecyclerView.bindItems(items: Pair<Rocket, List<Launch>>?) {
    items ?: return

    val rocket = items.first
    val launches = items.second

    withModels {

        val groupedLaunches = launches.groupBy { it.year }

        launchRocketDescription {
            id("description")
            description(rocket.description)
        }

        if (launches.isNotEmpty()) {

            launchChart {
                id("chart")
                chartEntries(groupedLaunches
                    .let { launchMap -> launchMap.mapValues { it.value.count() } }
                )
            }

            groupedLaunches
                .forEach {
                    launchYear {
                        id(it.key)
                        year(it.key)
                    }

                    it.value.forEach {
                        launchItem {
                            id(it.hashCode())
                            missionPatchUrl(it.missionPatchUrl)
                            missionName(it.missionName)
                            launchDate(it.date)
                            successful(it.successful)
                        }
                    }
                }
        }
    }
}
