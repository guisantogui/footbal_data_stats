package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Team
import dagger.Module
import dagger.Provides
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject

class TeamRepository @Inject constructor() {

    fun getTeamByName(realm: Realm, name: String): Team? {
        val team = realm.where(Team::class.java).equalTo("name", name, Case.INSENSITIVE).findFirst()

        return team
    }

    fun insertTeam(realm: Realm, teamName: String): Team {
        val team = Team(UUID.randomUUID(), teamName)
        realm.executeTransaction {
            it.insert(team)
        }

        return team
    }

    fun getAllTeamsByChampionship(realm: Realm, currentChampionshipId: UUID): RealmResults<Team> {
        val teams = realm.where(Team::class.java).findAll()
        return teams
    }
}