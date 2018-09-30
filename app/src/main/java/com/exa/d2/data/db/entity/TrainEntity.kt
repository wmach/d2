package com.exa.d2.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.exa.d2.data.model.Train

@Entity(tableName = "train")
data class TrainEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var name: String,
        var company: String,
        var lastupdate_gmt: Long,
        var source: String
)
fun TrainEntity.toTrain() = Train(name, company, lastupdate_gmt, source)