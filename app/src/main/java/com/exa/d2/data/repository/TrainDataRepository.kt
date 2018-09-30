package com.exa.d2.data.repository

import android.support.annotation.CheckResult
import android.util.Log
import com.exa.d2.data.api.TrainApi
import com.exa.d2.data.db.TrainDatabase
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.data.db.entity.toTrain
import com.exa.d2.data.model.toEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrainDataRepository @Inject constructor(
        private val api: TrainApi,
        private val db: TrainDatabase
) : TrainRepository {
    companion object {
        const val DEBUG = false
        const val TAG = "TrainDataRepository"
    }

    override val trains: Flowable<List<TrainEntity>> =
            db.getAllTrains()
                    .filter { it.isNotEmpty() }

    override fun refreshTrain(): Completable = api.getDelay()
            .filter { it.isNotEmpty() }
            .doOnSuccess {
                val list = it.map {
                    it.toEntity()
                }.toList()
                db.saveTrains(list)
            }.subscribeOn(Schedulers.io())
            .flatMapCompletable {
                Single.just(it).toCompletable()
            }

    override fun getTrain() = api.getDelay()
            .subscribeOn(Schedulers.io())
            .toFlowable()

    @CheckResult
    override fun loadTrain() = db.getAllTrains()
            .filter { it.isNotEmpty() }
            .map {
                it.map { it }
            }.subscribeOn(Schedulers.io())

    override fun saveTrain(trains: List<TrainEntity>): Completable {
        val result = Completable.create {
            db.saveTrains(trains)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
        return result
    }

}