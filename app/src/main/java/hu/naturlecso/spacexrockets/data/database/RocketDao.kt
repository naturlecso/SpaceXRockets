package hu.naturlecso.spacexrockets.data.database

import androidx.room.*
import io.reactivex.Flowable

@Dao
abstract class RocketDao {

    @Query("SELECT * FROM RocketDataModel")
    abstract fun getAll(): Flowable<List<RocketDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(rockets: List<RocketDataModel>)

    @Query("DELETE FROM RocketDataModel")
    abstract fun deleteAll()

    @Transaction
    open fun replaceAll(rockets: List<RocketDataModel>) {
        deleteAll()
        insertAll(rockets)
    }
}
