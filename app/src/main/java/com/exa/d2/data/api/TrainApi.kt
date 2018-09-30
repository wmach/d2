package com.exa.d2.data.api

import android.support.annotation.CheckResult
import com.exa.d2.data.model.Train
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TrainApi {
    @GET("delay.json")
    @CheckResult
    fun getDelay(): Single<List<Train>>
}
