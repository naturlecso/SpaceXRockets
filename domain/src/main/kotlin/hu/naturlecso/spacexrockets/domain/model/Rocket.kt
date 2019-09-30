package hu.naturlecso.spacexrockets.domain.model

data class Rocket(
    val id: String,
    val name: String,
    val description: String,
    val country: String,
    val engineCount: Int,
    val active: Boolean
)
