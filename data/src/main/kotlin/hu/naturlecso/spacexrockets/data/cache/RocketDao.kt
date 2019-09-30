package hu.naturlecso.spacexrockets.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable

@Dao
abstract class RocketDao {

    @Query("SELECT * FROM RocketDataModel")
    abstract fun getAll(): Flowable<List<RocketDataModel>>

    @Query("SELECT * FROM RocketDataModel WHERE id = :id")
    abstract fun get(id: String): Flowable<RocketDataModel>

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
