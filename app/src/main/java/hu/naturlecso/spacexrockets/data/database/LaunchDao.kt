package hu.naturlecso.spacexrockets.data.database

import androidx.room.*
import io.reactivex.Flowable

@Dao
abstract class LaunchDao {

    @Query("SELECT * FROM LaunchDataModel WHERE rocketId = :rocketId")
    abstract fun getAllByRocket(rocketId: String): Flowable<List<LaunchDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(launches: List<LaunchDataModel>)

    @Query("DELETE FROM LaunchDataModel")
    abstract fun deleteAll()

    @Transaction
    open fun replaceAll(launches: List<LaunchDataModel>) {
        deleteAll()
        insertAll(launches)
    }
}
