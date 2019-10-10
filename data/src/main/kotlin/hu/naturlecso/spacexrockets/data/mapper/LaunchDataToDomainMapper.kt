package hu.naturlecso.spacexrockets.data.mapper

import hu.naturlecso.spacexrockets.common.entity.Mapper
import hu.naturlecso.spacexrockets.data.cache.LaunchDataModel
import hu.naturlecso.spacexrockets.domain.model.Launch
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

class LaunchDataToDomainMapper : Mapper<LaunchDataModel, Launch> {

    override fun map(model: LaunchDataModel): Launch = model.let {
        Launch(
            missionName = it.missionName,
            date = Instant.ofEpochSecond(it.date).atZone(ZoneId.systemDefault()).toLocalDate(),
            year = it.year,
            successful = it.successful,
            missionPatchUrl = it.missionPatchUrl
        )
    }
}
