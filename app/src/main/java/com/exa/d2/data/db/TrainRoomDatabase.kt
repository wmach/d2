package com.exa.d2.data.db

import android.util.Log
import com.exa.d2.data.db.dao.TrainDao
import com.exa.d2.data.db.entity.TrainEntity
import io.reactivex.Flowable
import javax.inject.Inject

class TrainRoomDatabase @Inject constructor(
        private val database: AppDatabase,
        private val trainDao: TrainDao
): TrainDatabase {
    companion object {
        const val TAG = "TrainRoomDatabase"
    }

    override fun getAllTrains(): Flowable<List<TrainEntity>> = trainDao.getAllTrains()
    override fun saveTrains(trians: List<TrainEntity>) {
        database.runInTransaction {
            trainDao.clearAndInsert(trians)
        }
    }
}