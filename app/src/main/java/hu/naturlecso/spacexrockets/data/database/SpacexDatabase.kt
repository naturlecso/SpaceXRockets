package hu.naturlecso.spacexrockets.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [RocketDataModel::class, LaunchDataModel::class]
)
abstract class SpacexDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao
    abstract fun launchDao(): LaunchDao
}
