package com.exa.d2.data.model

import com.exa.d2.data.db.entity.TrainEntity

data class Train(val name: String
                 , val company: String
                 , val lastupdate_gmt: Long
                 , val source: String)
fun Train.toEntity() = TrainEntity(
        id = 0L
        , name = this.name
        , company = this.company
        , lastupdate_gmt = this.lastupdate_gmt
        , source = this.source)