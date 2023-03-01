package com.curuto.footballdata.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.joda.time.DateTime
import java.util.*


@RealmClass
open class Match(
        @PrimaryKey var id: UUID,
        var homeTeam: Team?,
        var awayTeam: Team?,
        var date: Date,
        var result: String, // "A", "H", "D"
        var homeTeamGoals: Int,
        var awayTeamGoals: Int,

        ): RealmObject() {

        constructor(): this(UUID.randomUUID(), null,null, Date(), "", 0,0)
}


