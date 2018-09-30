package com.exa.d2.data.repository

import android.support.annotation.CheckResult
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.data.model.Train
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrainRepository {
    val trains: Flowable<List<TrainEntity>>

    @CheckResult
    fun loadTrain(): Flowable<List<TrainEntity>>

    fun getTrain(): Flowable<List<Train>>

    @CheckResult
    fun refreshTrain(): Completable

    fun saveTrain(trains: List<TrainEntity>): Completable
}
