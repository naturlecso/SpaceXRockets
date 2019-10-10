package hu.naturlecso.spacexrockets.data.mapper

import hu.naturlecso.spacexrockets.common.entity.Mapper
import hu.naturlecso.spacexrockets.data.api.LaunchApiModel
import hu.naturlecso.spacexrockets.data.cache.LaunchDataModel

class LaunchApiToDataMapper : Mapper<LaunchApiModel, LaunchDataModel> {

    override fun map(model: LaunchApiModel): LaunchDataModel = model.let {
        LaunchDataModel(
            id = it.flightNumber,
            missionName = it.missionName,
            date = it.launchDate,
            year = it.launchYear,
            successful = it.launchSuccess,
            missionPatchUrl = it.links.missionPatch,
            rocketId = it.rocket.rocketId
        )
    }
}
