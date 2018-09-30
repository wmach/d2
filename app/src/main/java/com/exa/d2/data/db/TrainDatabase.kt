package com.exa.d2.data.db

import com.exa.d2.data.db.entity.TrainEntity
import io.reactivex.Flowable

interface TrainDatabase {
    fun getAllTrains(): Flowable<List<TrainEntity>>
    fun saveTrains(trians: List<TrainEntity>)
}