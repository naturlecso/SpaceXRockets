package hu.naturlecso.spacexrockets.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RocketDataModel(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val country: String,
    val engineCount: Int,
    val active: Boolean
)

@Entity
data class LaunchDataModel(
    @PrimaryKey val id: Int,
    val missionName: String,
    val date: Long,
    val year: Int,
    val successful: Boolean,
    val missionPatchUrl: String,
    val rocketId: String
)
