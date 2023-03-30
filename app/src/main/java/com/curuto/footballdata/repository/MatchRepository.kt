package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Match
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import java.util.*
import javax.inject.Inject

class MatchRepository @Inject constructor() {

    @Inject
    lateinit var realm: Realm

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun insertMatch(match: Match) {
        realm.executeTransaction {
            it.insertOrUpdate(match)
        }
    }

    fun getAllMatchesByChampionship(championshipId: UUID): List<Match>? {
        val championship = realm.where(Championship::class.java).equalTo("id", championshipId).findFirst()
        val matches = championship?.seasonList?.flatMap { it -> it.matches }

        return matches
    }
}