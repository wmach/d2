package com.exa.d2.di

import android.app.Application
import android.arch.persistence.room.Room
import com.exa.d2.data.db.AppDatabase
import com.exa.d2.data.db.TrainDatabase
import com.exa.d2.data.db.TrainRoomDatabase
import com.exa.d2.data.db.dao.TrainDao
import com.exa.d2.presentation.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule {

    companion object {
        val instance = DatabaseModule()
    }

    @Singleton
    @Provides
    open fun provideTrainDatabase(db: AppDatabase, dao: TrainDao): TrainDatabase = TrainRoomDatabase(db, dao)

    @Singleton
    @Provides
    open fun provideDb(app: App): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "train.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun provideTrainDao(db: AppDatabase): TrainDao = db.trainDao()

}
