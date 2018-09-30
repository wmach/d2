package com.exa.d2.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.exa.d2.data.db.dao.TrainDao
import com.exa.d2.data.db.entity.TrainEntity

@Database(
        entities = [
            (TrainEntity::class)
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainDao(): TrainDao
}
