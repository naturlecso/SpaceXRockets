package hu.naturlecso.spacexrockets.data.mapper

import hu.naturlecso.spacexrockets.common.entity.Mapper
import hu.naturlecso.spacexrockets.data.cache.RocketDataModel
import hu.naturlecso.spacexrockets.domain.model.Rocket

class RocketDataToDomainMapper : Mapper<RocketDataModel, Rocket> {

    override fun map(model: RocketDataModel): Rocket = model.let {
        Rocket(
            id = it.id,
            name = it.name,
            description = it.description,
            country = it.country,
            engineCount = it.engineCount,
            active = it.active
        )
    }
}
