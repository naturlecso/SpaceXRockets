package hu.naturlecso.spacexrockets.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketApiModel(
    @SerialName("rocket_id") val rocketId: String,
    @SerialName("rocket_name") val rocketName: String,
    val description: String,
    val country: String,
    val active: Boolean,
    val engines: EngineApiModel
)

@Serializable
data class EngineApiModel(
    val number: Int
)

@Serializable
data class LaunchApiModel(
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("mission_name") val missionName: String,
    @SerialName("launch_year") val launchYear: Int,
    @SerialName("launch_date_unix") val launchDate: Long,
    @SerialName("launch_success") val launchSuccess: Boolean,
    val links: LinksApiModel,
    val rocket: RocketIdDataModel
)

@Serializable
data class LinksApiModel(
    @SerialName("mission_patch_small") val missionPatch: String
)

@Serializable
data class RocketIdDataModel(
    @SerialName("rocket_id") val rocketId: String
)
