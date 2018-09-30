package com.exa.d2.presentation.main

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.data.model.Train
import com.exa.d2.data.model.toEntity
import com.exa.d2.data.repository.TrainRepository
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val trainRepository: TrainRepository
) : ViewModel(), LifecycleObserver {
    companion object {
        const val TAG = "MainViewModel"
    }

    private val compositeDisposable = CompositeDisposable()

    val trains = MutableLiveData<List<TrainEntity>>()

    fun refreshTrain() {
        trainRepository.refreshTrain().subscribe()
    }

    fun loadTrain(): Flowable<List<TrainEntity>> {
        return trainRepository.loadTrain()
    }

    fun getTrain() {
        trainRepository.getTrain().subscribeBy{
            val list = it.map {
                it.toEntity()
            }.toList()
            trains.postValue(list)
        }.addTo(compositeDisposable)
    }

    fun saveTrain() {
        val list = trains.value!!
        if (list.isEmpty()) return
        trainRepository.saveTrain(list)
                .subscribe()
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
