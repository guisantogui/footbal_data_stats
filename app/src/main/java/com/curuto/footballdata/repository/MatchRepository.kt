package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Match
import io.realm.Realm
import javax.inject.Inject

class MatchRepository @Inject constructor() {

    @Inject
    lateinit var realm: Realm

    fun insertMatch(match: Match) {
        realm.executeTransaction {
            it.insertOrUpdate(match)
        }
    }
}