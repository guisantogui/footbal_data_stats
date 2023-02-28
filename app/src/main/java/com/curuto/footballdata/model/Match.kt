package com.curuto.footballdata.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class Match(
        @PrimaryKey val id: Int,
        val homeTeam: Team,
        val awayTeam: Team,
        val result: Char, // "A", "H", "D"
        val homeTeamGoals: Int,
        val awayTeamGoals: Int, ): RealmObject() {}




