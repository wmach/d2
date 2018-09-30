package com.exa.d2.data.db.dao

import android.arch.persistence.room.*
import android.support.annotation.CheckResult
import com.exa.d2.data.db.entity.TrainEntity
import io.reactivex.Flowable

@Dao
abstract class TrainDao {

    @CheckResult
    @Query("SELECT * FROM train")
    abstract fun getAllTrains(): Flowable<List<TrainEntity>>

    @Query("DELETE FROM train")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(trains: List<TrainEntity>)

    @Transaction
    open fun clearAndInsert(newTrains: List<TrainEntity>) {
        deleteAll()
        insert(newTrains)
    }

}
