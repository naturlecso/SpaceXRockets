package hu.naturlecso.spacexrockets.domain

import org.threeten.bp.LocalDate

data class Launch(
    val missionName: String,
    val date: LocalDate,
    val year: Int,
    val successful: Boolean,
    val missionPatchUrl: String
)
