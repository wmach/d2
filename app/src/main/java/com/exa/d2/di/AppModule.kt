package com.exa.d2.di

import android.app.Application
import android.content.Context
import com.exa.d2.data.api.TrainApi
import com.exa.d2.data.db.AppDatabase
import com.exa.d2.data.db.TrainDatabase
import com.exa.d2.data.repository.TrainDataRepository
import com.exa.d2.data.repository.TrainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton @Provides @JvmStatic
    fun provideTrainRepository(trainApi: TrainApi,  trainDatabase: TrainDatabase): TrainRepository =
            TrainDataRepository(trainApi, trainDatabase)

}