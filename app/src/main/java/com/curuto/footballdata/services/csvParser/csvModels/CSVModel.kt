package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.DaggerRepositoryComponent
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import java.util.*
import javax.inject.Inject

abstract class CSVModel {

    open var columnModelList = listOf("")
    @Inject
    lateinit var teamRepository: TeamRepository

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun matchDownloadedModel(downloadedColumns: Array<String>) : Boolean {
        return columnModelList.all { downloadedColumns.contains(it) }
    }

    fun getTeam(realm: Realm, teamName: String): Team {

        var team = teamRepository.getTeamByName(realm, teamName)
        if(team == null){
            team = Team(UUID.randomUUID(), teamName)
        }

        return team
    }



    abstract fun getMatch(array: Array<String>): Match
    abstract fun getHomeTeam(array: Array<String>): Team
    abstract fun getAwayTeam(array: Array<String>): Team
}