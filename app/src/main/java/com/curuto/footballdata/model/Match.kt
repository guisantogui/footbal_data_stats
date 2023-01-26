package com.curuto.footballdata.model

import io.realm.annotations.PrimaryKey


data class Match(
        @PrimaryKey val id: Int,
        val homeTeam: Team,
        val awayTeam: Team,
        val result: Char, // "A", "H", "D"
        val homeTeamGoals: Int,
        val awayTeamGoals: Int, ) {}




