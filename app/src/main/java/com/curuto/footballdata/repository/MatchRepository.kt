package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Match
import io.realm.Realm
import java.util.*
import javax.inject.Inject

class MatchRepository @Inject constructor() {



    fun insertMatch(realm: Realm, match: Match) {
        realm.executeTransaction {
            it.insertOrUpdate(match)
        }
    }

    fun getAllMatchesByChampionship(realm: Realm, championshipId: UUID): List<Match>? {
        val championship = realm.where(Championship::class.java).equalTo("id", championshipId).findFirst()
        val matches = championship?.seasonList?.flatMap { it -> it.matches }

        return matches
    }

}