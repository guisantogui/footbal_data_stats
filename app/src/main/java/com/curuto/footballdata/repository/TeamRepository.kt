package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Team
import io.realm.Case
import io.realm.Realm
import javax.inject.Inject

object TeamRepository {

    @Inject lateinit var realm: Realm

    fun getTeamByName(name: String): Team? {
        val team = realm.where(Team::class.java).equalTo("name", name, Case.INSENSITIVE).findFirst()

        return team
    }

    fun insertTeam(team: Team){
        realm.executeTransaction {
            it.insertOrUpdate(team)
        }
    }
}